package test.model.appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.model.appointment.Appointment;
import soft252.model.drugs.Drug;
import soft252.model.drugs.DrugRepository;
import soft252.model.drugs.Prescription;
import soft252.model.request.PrescriptionRequest;
import soft252.model.request.Request;
import soft252.model.request.RequestRepository;
import soft252.model.user.Doctor;
import soft252.model.user.Patient;
import soft252.model.user.info.Gender;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    private static Patient _patient;
    private static Prescription _drugPrescription;
    private static Prescription _prescription;
    private static Appointment _appointment;

    @BeforeEach
    public void setUpClass() {
        _patient = new Patient("9020", "John", "Cena", Gender.MALE);
        Doctor doctor = new Doctor("1000", "Ray", "Winston");

        Drug drug = new Drug("Paracetamol", "Painkiller");
        DrugRepository.getInstance().add(drug, 50);
        _drugPrescription = new Prescription(drug, 10, 5);

        DummyTreatment treatment = new DummyTreatment("Massage", "Soothes aches and pains");
        _prescription = new Prescription(treatment, 3, 3);

        _appointment = new Appointment(_patient, doctor, LocalDateTime.now());
        _appointment.addPrescription(_drugPrescription);
        _appointment.addPrescription(_prescription);
    }

    /**
     * Tests to see whether the complete function is fully operational.
     */
    @Test
    @DisplayName("complete")
    void complete() {
        _appointment.complete();

        assertTrue(_appointment.isCompleted());

        assertTrue(_patient.getPrescriptions().contains(_prescription));

        assertTrue(containsPrescriptionRequest(_patient, _drugPrescription));
    }

    /**
     * Searches to see if the prescription has been added to the RequestRepository.
     *
     * @param patient  the target patient.
     * @param prescription the target prescription.
     * @return TRUE if found, FALSE otherwise.
     */
    private boolean containsPrescriptionRequest(Patient patient, Prescription prescription){
        for (Request request : RequestRepository.getInstance().get())
        if(request instanceof PrescriptionRequest)
            if (( (PrescriptionRequest) request ).getPrescription().equals(prescription) &&
                    ( (PrescriptionRequest) request ).getPatient().equals(patient)) return true;

        return false;
    }
}