package soft252.model.appointment;

import soft252.model.I_Completable;
import soft252.model.user.Doctor;
import soft252.model.user.Patient;

import java.time.LocalDateTime;

/**
 * Defines the functions for an appointment object.
 */
public interface I_Appointment extends I_Completable {
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
