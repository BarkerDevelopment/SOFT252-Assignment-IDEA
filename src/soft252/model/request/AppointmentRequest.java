package soft252.model.request;

import soft252.model.appointment.Appointment;
import soft252.model.appointment.AppointmentRepository;
import soft252.model.user.Doctor;
import soft252.model.user.Patient;

import java.time.LocalDateTime;

/**
 * A class that encapsulates a request for an appointment.
 */
public class AppointmentRequest extends Request{

    private final Patient _patient;
    private Doctor _doctor;
    private LocalDateTime _dateTime;

    /**
     * Default constructor.
     * Additionally, this constructor adds the resultant object to its corresponding repository: RequestRepository.
     *
     * @param requester the patient requesting the appointment.
     * @param doctor the requested doctor.
     * @param dateTime the requested dateTime for the appointment.
     */
    public AppointmentRequest(Patient requester, Doctor doctor, LocalDateTime dateTime) {
        _patient = requester;
        _doctor = doctor;
        _dateTime = dateTime;

        RequestRepository.getInstance().add(this);
    }

    /**
     * @return the _patient variable.
     */
    public Patient getPatient() {
        return _patient;
    }

    /**
     * @return the _doctor variable.
     */
    public Doctor getDoctor() {
        return _doctor;
    }

    /**
     * @return the _dateTime variable.
     */
    public LocalDateTime getDateTime() {
        return _dateTime;
    }

    /**
     * @param doctor the new doctor of the appointment.
     */
    public void setDoctor(Doctor doctor) {
        _doctor = doctor;
    }

    /**
     * @param dateTime the new dateTime of the appointment.
     */
    public void setDateTime(LocalDateTime dateTime) {
        _dateTime = dateTime;
    }

    /**
     * The action following request approval.
     */
    @Override
    protected void approveAction() {
        AppointmentRepository.getInstance().add( new Appointment(_patient, _doctor, _dateTime) );

        sendMessage(_patient, String.format("You have an appointment with Dr. %s at %s.", _doctor.getSurname(), _dateTime.toString()));
        sendMessage(_doctor, String.format("%s (%s) has booked an appointment with you at %s.", _patient.getName(), _patient.getId().toString(), _dateTime.toString()));
    }

    /**
     * The action following request denial.
     */
    @Override
    protected void denyAction() {
        sendMessage(_patient, String.format("Your appointment request on %s with Dr. %s has been denied.", _dateTime.toString(), _doctor.getSurname()));
    }
}
