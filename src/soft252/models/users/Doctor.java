package soft252.models.users;

import soft252.models.appointments.AppointmentRepository;
import soft252.models.appointments.I_Appointment;
import soft252.models.appointments.I_AppointmentParticipant;
import soft252.models.request.I_Requester;
import soft252.models.request.DrugRequest;
import soft252.models.users.feedback.I_Feedback;
import soft252.models.users.feedback.I_FeedbackRecipient;
import soft252.models.users.info.Address;
import soft252.models.users.info.Role;
import soft252.models.users.messaging.I_MessageRecipient;
import soft252.models.users.messaging.I_MessageSender;
import soft252.models.users.messaging.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A User subclass for the system's doctor.
 */
public class Doctor extends User
    implements I_FeedbackRecipient, I_AppointmentParticipant, I_MessageSender, I_Requester< DrugRequest > {

    public static Role ROLE = Role.DOCTOR;
    private ArrayList<I_Feedback> _feedback;

    /**
     * Creates an Doctor object.
     *
     * @param name the Doctor's name.
     * @param surname the Doctor's surname.
     * @param address the Doctor's address
     * @param password the Doctor's password.
     */
    public Doctor(String name, String surname, Address address, int password) {
        super(ROLE, name, surname, address, password);
        _feedback = new ArrayList<>();
    }

    /**
     * Creates a dummy Doctor object for testing purposes.
     * NOTE: This constructor is used to specifically test the random ID functionality.
     *
     * @param name the Doctor's name.
     * @param surname the Doctor's surname.
     * @param password the Doctor's password.
     * @param seed the pseudo-random generator seed. This ensures repeatable random generation.
     */
    public Doctor(String name, String surname, int password, long seed) {
        super(ROLE, name, surname, password, seed);
        _feedback = new ArrayList<>();
    }

    /**
     * Creates a bare dummy Doctor object for testing purposes. The name and surname are used to provide another element of
     * individuality to increase ease of testing rather than just looking at IDs.
     *
     * @param idNumber the Doctor's ID number. This will be added to the Doctor role string to create the Doctor ID.
     * @param name the Doctor's name.
     * @param surname the Doctor's surname.
     */
    public Doctor(String idNumber, String name, String surname) {
        super(ROLE, idNumber, name, surname);
        _feedback = new ArrayList<>();
    }

    /**
     * NOTE: This should not be used by the recipient themselves. This function's intended to be used by the
     * I_FeedbackModerator.
     *
     * @return an ArrayList of all feedback.
     */
    @Override
    public ArrayList<I_Feedback> getFeedback() {
        return _feedback;
    }

    /**
     * Returns the feedback based on whether it is moderated or not.
     *
     * @param flag if TRUE returns all moderated feedback, if FALSE returns all un-moderated feedback.
     * @return an ArrayList of all moderated feedback.
     */
    @Override
    public ArrayList<I_Feedback> getFeedback(boolean flag) {
        ArrayList<I_Feedback> allFeedback = new ArrayList<>();

        for(I_Feedback feedback: _feedback) if(feedback.isModerated() == flag) allFeedback.add(feedback);

        return allFeedback;
    }

    /**
     * Adds a piece of feedback.
     *
     * @param feedback the new feedback.
     */
    @Override
    public void addFeedback(I_Feedback feedback) {
        _feedback.add(feedback);
    }

    /**
     * Removes a piece of feedback.
     *
     * @param feedback the target feedback.
     */
    @Override
    public void removeFeedback(I_Feedback feedback) {
        _feedback.remove(feedback);
    }

    /**
     * @return all appointments associated with the User.
     */
    @Override
    public ArrayList< I_Appointment > getAppointments() {
        return AppointmentRepository.getInstance().get(this);
    }

    /**
     * @return all future appointments associated with the User.
     */
    @Override
    public ArrayList< I_Appointment > getFutureAppointments() {
        ArrayList< I_Appointment > pastAppointments = AppointmentRepository.getInstance().get(this);
        // Remove all dates from the past.
        pastAppointments.removeIf(a -> a.getDateTime().compareTo(LocalDateTime.now()) < 0);

        return pastAppointments;
    }

    /**
     * @return all past appointments associated with the User.
     */
    @Override
    public ArrayList< I_Appointment > getPastAppointments() {
        ArrayList< I_Appointment > pastAppointments = AppointmentRepository.getInstance().get(this);
        // Remove all dates from the future.
        pastAppointments.removeIf(a -> a.getDateTime().compareTo(LocalDateTime.now()) >= 0);

        return pastAppointments;
    }

    /**
     * Sends a message to another doctor.
     *
     * @param recipient the target doctor.
     * @param message   the message to be sent.
     */
    @Override
    public void sendMessage(I_MessageRecipient recipient, String message) {
        recipient.addMessage(new Message(this, message));
    }
}
