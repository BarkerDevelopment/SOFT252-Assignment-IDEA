package soft252.model.user.messaging;

/**
 * Defines functions required for objects sending messages.
 */
public interface I_MessageSender {

    /**
     * Sends a message to another user.
     *
     * @param recipient the target user.
     * @param message the message to be sent.
     */
    public abstract void sendMessage(I_MessageRecipient recipient, I_Message message);
}
