package soft252.models.users;

import soft252.models.users.feedback.I_Feedback;
import soft252.models.users.feedback.I_FeedbackModerator;
import soft252.models.users.feedback.I_FeedbackRecipient;
import soft252.models.users.info.Address;
import soft252.models.users.info.Role;
import soft252.models.users.messaging.I_MessageRecipient;
import soft252.models.users.messaging.I_MessageSender;
import soft252.models.users.messaging.Message;

import java.util.ArrayList;

/**
 * A User subclass for the system administrators.
 */
public class Admin extends User
    implements I_MessageSender,
        I_FeedbackModerator {

    public static Role ROLE = Role.ADMIN;

    /**
     * Creates an Admin object.
     *
     * @param name the Admin's name.
     * @param surname the Admin's surname.
     * @param address the Admin's address
     * @param password the Admin's password.
     */
    public Admin(String name, String surname, Address address, String password) {
        super(ROLE, name, surname, address, password);
    }

    /**
     * Creates a dummy Admin object for testing purposes.
     * NOTE: This constructor is used to specifically test the random ID functionality.
     *
     * @param name the Admin's name.
     * @param surname the Admin's surname.
     * @param password the Admin's password.
     * @param seed the pseudo-random generator seed. This ensures repeatable random generation.
     */
    public Admin(String name, String surname, String password, long seed) {
        super(ROLE, name, surname, password, seed);
    }

    /**
     * Creates a bare dummy Admin object for testing purposes. The name and surname are used to provide another element of
     * individuality to increase ease of testing rather than just looking at IDs.
     *
     * @param idNumber the Admin's ID number. This will be added to the Admin role string to create the Admin ID.
     * @param name the Admin's name.
     * @param surname the Admin's surname.
     */
    public Admin(String idNumber, String name, String surname) {
        super(ROLE, idNumber, name, surname);
    }

    /**
     * Sends a message to another user.
     *
     * @param recipient the target user.
     * @param message   the message to be sent.
     */
    @Override
    public void sendMessage(I_MessageRecipient recipient, String message) {
        recipient.addMessage(new Message(this, message));
    }

    /**
     * Retrieves all the feedback from the recipient.
     *
     * @param recipient the target recipient.
     * @return an ArrayList of all feedback.
     */
    @Override
    public ArrayList<I_Feedback> getRecipientFeedback(I_FeedbackRecipient recipient) {
        return recipient.getFeedback();
    }

    /**
     * Retrieves all the feedback from the recipient that isn't moderated.
     *
     * @param recipient the target recipient.
     * @return an ArrayList of all un-moderated feedback.
     */
    @Override
    public ArrayList<I_Feedback> getUnmoderatedFeedback(I_FeedbackRecipient recipient) {
        return recipient.getFeedback(false);
    }

    /**
     * Confirms the feedback has been moderated.
     *
     * @param feedback the target feedback.
     */
    @Override
    public void moderateFeedback(I_Feedback feedback) {
        feedback.moderate();
    }

    /**
     * Edits the target feedback with a new feedback string, then confirms it's been moderated.
     *
     * @param feedback the target feedback.
     * @param string   the new feedback string.
     */
    @Override
    public void moderateFeedback(I_Feedback feedback, String string) {
        feedback.setFeedback(string);
        feedback.moderate();
    }

    /**
     * Deletes a target feedback from a target recipient.
     *
     * @param recipient the target recipient.
     * @param feedback  the target feedback.
     */
    @Override
    public void deleteFeedback(I_FeedbackRecipient recipient, I_Feedback feedback) {
        recipient.removeFeedback(feedback);
    }
}
