package soft252.models.appointments;

import soft252.models.I_Completable;
import soft252.models.I_RepositoryItem;
import soft252.models.users.Doctor;
import soft252.models.users.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Defines the functions for an appointment object.
 */
public interface I_Appointment extends I_Completable, I_RepositoryItem< I_Appointment > {
    /**
     * @return the participants of the appointment.
     */
    public abstract ArrayList<I_AppointmentParticipant> getParticipants();

    /**
     * @return the _patient variable.
     */
    public abstract Patient getPatient();

    /**
     *
     * @return the _doctor variable.
     */
    public abstract Doctor getDoctor();

    /**
     *
     * @return the _dateTime variable.
     */
    public abstract LocalDateTime getDateTime();
}
