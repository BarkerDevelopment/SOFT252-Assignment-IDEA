package test.models.appointments;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.models.appointments.Appointment;
import soft252.models.appointments.AppointmentRepository;
import soft252.models.appointments.I_Appointment;
import soft252.models.users.Doctor;
import soft252.models.users.Patient;
import soft252.models.users.info.Gender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PatientAppointmentParticipantTest {
    private static AppointmentRepository _repo = AppointmentRepository.getInstance();
    private static Patient _patient;
    private static ArrayList< I_Appointment > _past;
    private static ArrayList<I_Appointment> _future;

    @BeforeAll
    static void setup(){
        _patient = new Patient("3252", "John"," Cena", Gender.MALE);
        Doctor doctor = new Doctor("9231", "Doctor", "Who");
        Doctor altDoctor = new Doctor("2623", "Brian", "Cox");

        _future = new ArrayList<>(Arrays.asList(
                new Appointment(_patient, doctor, LocalDateTime.of(2020, 1, 4, 14, 25)),
                new Appointment(_patient, altDoctor, LocalDateTime.of(2019, 12, 31, 10, 0))
        ));

        _past = new ArrayList<>(Arrays.asList(
                new Appointment(_patient, doctor, LocalDateTime.of(2019, 12, 14, 10, 20)),
                new Appointment(_patient, altDoctor, LocalDateTime.of(2019, 5, 2, 14, 30))
        ));

        _past.forEach(a -> a.complete());

        _past.forEach(a -> _repo.add(a));
        _future.forEach(a -> _repo.add(a));
    }

    @Test
    @DisplayName("get all")
    void getAppointments() {
        ArrayList<I_Appointment> expResult = new ArrayList<>();
        expResult.addAll(_past);
        expResult.addAll(_future);
        ArrayList<I_Appointment> result = _patient.getAppointments();

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("get future")
    void getFutureAppointments() {
        ArrayList<I_Appointment> expResult = _future;
        ArrayList<I_Appointment> result = _patient.getFutureAppointments();

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("get past")
    void getPastAppointments() {
        ArrayList<I_Appointment> expResult = _past;
        ArrayList<I_Appointment> result = _patient.getPastAppointments();

        assertEquals(expResult, result);
    }
}