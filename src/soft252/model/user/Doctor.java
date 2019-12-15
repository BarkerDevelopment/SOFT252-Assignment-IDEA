package soft252.model.user;

import soft252.model.user.feedback.I_Feedback;
import soft252.model.user.feedback.I_FeedbackRecipient;
import soft252.model.user.info.Address;
import soft252.model.user.info.Role;
import soft252.model.user.messaging.I_Message;
import soft252.model.user.messaging.I_MessageRecipient;
import soft252.model.user.messaging.I_MessageSender;

import java.util.ArrayList;

/**
 * A User subclass for the system's doctor.
 */
public class Doctor extends User
    implements I_MessageSender,
        I_FeedbackRecipient {

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
    public Doctor(String name, String surname, Address address, String password) {
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
    public Doctor(String name, String surname, String password, long seed) {
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
     * Sends a message to another doctor.
     *
     * @param recipient the target doctor.
     * @param message   the message to be sent.
     */
    @Override
    public void sendMessage(I_MessageRecipient recipient, I_Message message) {
        recipient.addMessage(message);
    }
}
