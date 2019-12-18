package soft252.model.request;

import soft252.model.appointment.Appointment;
import soft252.model.appointment.AppointmentRepository;
import soft252.model.appointment.I_Appointment;
import soft252.model.user.Doctor;
import soft252.model.user.Patient;
import soft252.model.user.messaging.Message;

import java.time.LocalDateTime;

/**
 *
 */
public class AppointmentRequest extends Request
    implements I_Appointment {

    private final Patient _patient;
    private Doctor _doctor;
    private LocalDateTime _dateTime;

    public AppointmentRequest(Patient requester, Doctor doctor, LocalDateTime dateTime) {
        _patient = requester;
        _doctor = doctor;
        _dateTime = dateTime;
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

        _patient.addMessage(new Message(String.format("You have an appointment with Dr. %s at %s.", _doctor.getSurname(), _dateTime.toString())));
        _doctor.addMessage(new Message(String.format("%s (%s) has booked an appointment with you at %s.", _patient.getName(), _patient.getId().toString(), _dateTime.toString()) ) );
    }

    /**
     * The action following request denial.
     */
    @Override
    protected void denyAction() {
        _patient.addMessage(new Message(String.format("Your appointment request on %s with Dr. %s has been denied.", _dateTime.toString(), _doctor.getSurname()) ) );
    }
}
