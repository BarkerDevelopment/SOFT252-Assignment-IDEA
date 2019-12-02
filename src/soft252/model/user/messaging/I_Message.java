package soft252.model.user.messaging;

import java.time.LocalDateTime;

/**
 * Defines functions required for messages for inter-user messaging.
 */
public interface I_Message {
    /**
     * @return the _message variable. Represents the contents of the message.
     */
    public String getMessage();

    /**
     * @return the _datetime variable. Represents the time and date of when the message was sent.
     */
    public LocalDateTime getDatetime();

    /**
     * @return the _sender variable. Represents the sender of the message.
     */
    public I_MessageSender getSender();

    // Setters are not included in the interface as they shouldn't be changed after the message is sent.
}
