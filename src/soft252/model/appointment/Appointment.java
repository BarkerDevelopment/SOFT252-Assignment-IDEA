package soft252.model.appointment;

import soft252.model.I_Completable;
import soft252.model.drugs.Drug;
import soft252.model.drugs.Prescription;
import soft252.model.drugs.I_Prescription;
import soft252.model.request.PrescriptionRequest;
import soft252.model.request.RequestRepository;
import soft252.model.user.Doctor;
import soft252.model.user.Patient;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that encapsulates patient's appointments.
 */
public class Appointment
        implements I_Appointment, I_Completable {

    private final Patient _patient;
    private Doctor _doctor;
    private LocalDateTime _dateTime;
    private String _notes;
    private ArrayList< I_Prescription > _prescriptions;
    private boolean _isCompleted;

    /**
     * Default appointment constructor.
     *
     * @param patient the patient.
     * @param doctor the doctor.
     * @param dateTime the date and time of the appointment.
     */
    public Appointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        _patient = patient;
        _doctor = doctor;
        _dateTime = dateTime;
        _prescriptions = new ArrayList<>();
        _isCompleted = false;
    }

    /**
     * @return the participants of the appointment.
     */
    @Override
    public ArrayList< I_AppointmentParticipant > getParticipants() {
        return new ArrayList<>(Arrays.asList(_doctor, _patient));
    }

    /**
     * @return the _patient variable.
     */
    @Override
    public Patient getPatient() {
        return _patient;
    }

    /**
     * @return the _doctor variable.
     */
    @Override
    public Doctor getDoctor() {
        return _doctor;
    }

    /**
     * @return the _dateTime variable.
     */
    @Override
    public LocalDateTime getDateTime() {
        return _dateTime;
    }

    /**
     *
     * @return the _notes variable.
     */
    public String getNotes() {
        return _notes;
    }

    /**
     * @return the _prescriptions variable.
     */
    public ArrayList<I_Prescription> getPrescriptions() {
        return _prescriptions;
    }

    /**
     * @return the _isCompleted variable. This represents if object has been completed.
     */
    @Override
    public boolean isCompleted() {
        return _isCompleted;
    }

    /**
     * @param doctor the value to set _doctor to.
     */
    public void setDoctor(Doctor doctor) {
        _doctor = doctor;
    }

    /**
     * @param dateTime the value to set _dateTime to.
     */
    public void setDateTime(LocalDateTime dateTime) {
        _dateTime = dateTime;
    }

    /**
     * @param notes the value to set _notes to.
     */
    public void setNotes(String notes) {
        _notes = notes;
    }

    /**
     * @param prescriptions the value to set _prescriptions to.
     */
    public void setPrescriptions(ArrayList<I_Prescription> prescriptions) {
        _prescriptions = prescriptions;
    }

    /**
     * Add a prescription to the list of prescriptions.
     *
     * @param prescription the new prescription.
     */
    public void addPrescription(I_Prescription prescription){
        _prescriptions.add(prescription);
    }

    /**
     * Remove a prescription for the list of prescriptions.
     *
     * @param index the target prescription's index.
     */
    public void removePrescription(int index){
        _prescriptions.remove(index);
    }

    /**
     * Completes an appointment. The given prescriptions will then be assigned to the user.
     */
    @Override
    public void complete() {
        _isCompleted = true;

        _prescriptions.forEach( prescription -> {
            if(prescription.getTreatment() instanceof Drug){
                RequestRepository.getInstance().add( new PrescriptionRequest(_patient, prescription) );

            }else{
                _patient.addPrescription(prescription); // If a prescription is not of a drug, just assign it to the patient.
            }
        });
    }
}
