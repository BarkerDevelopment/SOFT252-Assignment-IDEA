package soft252.model.user;

import soft252.model.user.info.Address;
import soft252.model.user.info.Role;
import soft252.model.user.messaging.I_Message;
import soft252.model.user.messaging.I_MessageRecipient;
import soft252.model.user.messaging.I_MessageSender;
import soft252.model.user.messaging.Message;

/**
 * A User subclass for the system's secretaries.
 */
public class Secretary extends User
    implements I_MessageSender {

    public static Role ROLE = Role.SECRETARY;

    /**
     * Creates an Secretary object.
     *
     * @param name the Secretary's name.
     * @param surname the Secretary's surname.
     * @param address the Secretary's address
     * @param password the Secretary's password.
     */
    public Secretary(String name, String surname, Address address, String password) {
        super(ROLE, name, surname, address, password);
    }

    /**
     * Creates a dummy Secretary object for testing purposes.
     * NOTE: This constructor is used to specifically test the random ID functionality.
     *
     * @param name the Secretary's name.
     * @param surname the Secretary's surname.
     * @param password the Secretary's password.
     * @param seed the pseudo-random generator seed. This ensures repeatable random generation.
     */
    public Secretary(String name, String surname, String password, long seed) {
        super(ROLE, name, surname, password, seed);
    }

    /**
     * Creates a bare dummy Secretary object for testing purposes. The name and surname are used to provide another element of
     * individuality to increase ease of testing rather than just looking at IDs.
     *
     * @param idNumber the Secretary's ID number. This will be added to the Secretary role string to create the Secretary ID.
     * @param name the Secretary's name.
     * @param surname the Secretary's surname.
     */
    public Secretary(String idNumber, String name, String surname) {
        super(ROLE, idNumber, name, surname);
    }


    /**
     * Sends a message to another user.
     *
     * @param recipient the target secretary.
     * @param message   the message to be sent.
     */
    @Override
    public void sendMessage(I_MessageRecipient recipient, String message) {
        recipient.addMessage(new Message(this, message));
    }
}
