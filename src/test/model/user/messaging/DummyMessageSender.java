package test.model.user.messaging;

import soft252.model.user.messaging.I_Message;
import soft252.model.user.messaging.I_MessageRecipient;
import soft252.model.user.messaging.I_MessageSender;
import soft252.model.user.messaging.Message;

/**
 * A test class for message sending.
 */
public class DummyMessageSender implements I_MessageSender {
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
}
