package soft252.model.user.messaging;

import java.util.ArrayList;

/**
 * Defines functions required for objects receiving messages.
 */
public interface I_MessageRecipient {
    /**
     * @return the _messages variable.
     */
    public abstract ArrayList<I_Message> getMessages();

    /**
     * Adds the new message to the message array.
     *
     * @param message the new message.
     */
    public abstract void addMessage(I_Message message);


}
