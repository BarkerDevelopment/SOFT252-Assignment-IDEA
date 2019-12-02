package soft252.model.user.messaging;

import soft252.model.I_Printable;
import soft252.model.user.User;

import java.time.LocalDateTime;

/**
 * A class that encapsulates messages that get passed between users.
 */
public class Message
        implements I_Message, I_Printable {

    private String _message;
    private LocalDateTime _datetime;
    private I_MessageSender _sender;

    /**
     * Creates a message object.
     *
     * @param message the content of the message.
     * @param sender the sender of the message.
     */
    public Message(String message, I_MessageSender sender){
        _message = message;
        _datetime = LocalDateTime.now();
        _sender = sender;
    }

    /**
     * @return the _message variable. Represents the contents of the message.
     */
    @Override
    public String getMessage() {
        return _message;
    }

    /**
     * @return the _datetime variable. Represents the time and date of when the message was sent.
     */
    @Override
    public LocalDateTime getDatetime() {
        return _datetime;
    }

    /**
     * @return the _sender variable. Represents the sender of the message.
     */
    @Override
    public I_MessageSender getSender() {
        return _sender;
    }

    /**
     * @param message the new contents of the _message variable.
     */
    public void setMessage(String message) {
        _message = message;
    }

    /**
     * @param datetime the new content of the _datetime variable.
     */
    public void setDatetime(LocalDateTime datetime) {
        _datetime = datetime;
    }

    /**
     * @param sender the new content of the _sender variable.
     * @throws ClassCastException if sender doesn't inherit from the I_MessageSender interface.
     */
    public void setSender(User sender) {
        if(sender instanceof I_MessageSender) {
            _sender = (I_MessageSender) sender;

        }else{
            throw new ClassCastException();
        }
    }
}
