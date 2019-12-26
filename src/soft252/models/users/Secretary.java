package soft252.models.users;

import soft252.models.request.I_RequestAdjudicator;
import soft252.models.request.Request;
import soft252.models.users.info.Address;
import soft252.models.users.info.Role;
import soft252.models.users.messaging.I_MessageRecipient;
import soft252.models.users.messaging.I_MessageSender;
import soft252.models.users.messaging.Message;

/**
 * A User subclass for the system's secretaries.
 */
public class Secretary extends User
    implements I_RequestAdjudicator, I_MessageSender {

    public static Role ROLE = Role.SECRETARY;

    /**
     * Creates an Secretary object.
     *
     * @param name the Secretary's name.
     * @param surname the Secretary's surname.
     * @param address the Secretary's address
     * @param password the Secretary's password.
     */
    public Secretary(String name, String surname, Address address, int password) {
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
    public Secretary(String name, String surname, int password, long seed) {
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
     * Approve the passed request.
     *
     * @param request the target request.
     */
    @Override
    public boolean approveRequest(Request request) {
        try{
            request.approve();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Defines the passed request.
     *
     * @param request the target request.
     */
    @Override
    public boolean declineRequest(Request request) {
        try{
            request.deny();
            return true;
        } catch (Exception e) {
            return false;
        }
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
