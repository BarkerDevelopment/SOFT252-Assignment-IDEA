package soft252.model.appointment;

import java.util.ArrayList;

/**
 * Defines the functions of a user that participates in appointments
 */
public interface I_AppointmentParticipant {
    /**
     * @return all appointments associated with the User.
     */
    public abstract ArrayList<I_Appointment> getAppointments();

    /**
     * @return all future appointments associated with the User.
     */
    public abstract ArrayList<I_Appointment> getFutureAppointments();

    /**
     * @return all past appointments associated with the User.
     */
    public abstract ArrayList<I_Appointment>getPastAppointments();
}
