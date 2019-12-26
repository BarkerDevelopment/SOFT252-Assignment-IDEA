package test.models.users.messaging;

import soft252.models.users.messaging.I_Message;
import soft252.models.users.messaging.I_MessageRecipient;

import java.util.ArrayList;

/**
 * A test class for message receiving.
 */
public class DummyMessageRecipient implements I_MessageRecipient {
    private final ArrayList< I_Message > _messages;

    /**
     * Default constructor.
     */
    public DummyMessageRecipient() {
        _messages = new ArrayList<>();
    }

    /**
     * @return the _messages variable.
     */
    @Override
    public ArrayList< I_Message > getMessages() {
        return _messages;
    }

    /**
     * Adds the new message to the message array.
     *
     * @param message the new message.
     */
    @Override
    public void addMessage(I_Message message) {
        _messages.add(message);
    }
}
