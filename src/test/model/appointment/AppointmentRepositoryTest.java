package test.model.appointment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.model.appointment.Appointment;
import soft252.model.appointment.AppointmentRepository;
import soft252.model.appointment.I_Appointment;
import soft252.model.user.Doctor;
import soft252.model.user.Patient;
import soft252.model.user.info.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentRepositoryTest {
    private static AppointmentRepository _repo = AppointmentRepository.getInstance();
    private static Doctor _doctor;
    private static Patient _patient;
    private static I_Appointment _sharedAppointment;
    private static I_Appointment _doctorAppointment;
    private static I_Appointment _patientAppointment;
    private static LocalDate _shareDate;

    @BeforeAll
    static void setup(){
        _doctor = new Doctor("1111", "Ray", "Winston");
        Doctor altDoctor = new Doctor("9231", "Doctor", "Who");
        _patient = new Patient("3252", "John"," Cena", Gender.MALE);
        Patient altPatient = new Patient("2623", "Gemma", "Stone", Gender.FEMALE);

        _sharedAppointment = new Appointment(_patient, _doctor, LocalDateTime.now());
        _shareDate = LocalDate.of(2019, 11, 23);
        _doctorAppointment = new Appointment(altPatient, _doctor, LocalDateTime.of(_shareDate, LocalTime.of(19, 30)));
        _patientAppointment = new Appointment(_patient, altDoctor, LocalDateTime.of(_shareDate, LocalTime.of(12, 45)));

        _repo.add(_sharedAppointment);
        _repo.add(_doctorAppointment);
        _repo.add(_patientAppointment);
    }

    @Test
    @DisplayName("get all for a specific doctor")
    void get() {
        ArrayList<I_Appointment> expResult = new ArrayList<>(Arrays.asList(_sharedAppointment, _doctorAppointment));
        ArrayList<I_Appointment> result = AppointmentRepository.getInstance().get(_doctor);

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("get all for specific patient")
    void testGet() {
        ArrayList<I_Appointment> expResult = new ArrayList<>(Arrays.asList(_sharedAppointment, _patientAppointment));
        ArrayList<I_Appointment> result = AppointmentRepository.getInstance().get(_patient);

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("get all for specific date")
    void testGet1(){
        ArrayList<I_Appointment> expResult = new ArrayList<>(Arrays.asList(_doctorAppointment, _patientAppointment));
        ArrayList<I_Appointment> result = AppointmentRepository.getInstance().get(_shareDate);

        assertEquals(expResult, result);
    }
}