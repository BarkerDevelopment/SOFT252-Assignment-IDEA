package soft252.model.request;

import soft252.model.I_RepositoryItem;
import soft252.model.user.messaging.I_MessageRecipient;
import soft252.model.user.messaging.I_MessageSender;
import soft252.model.user.messaging.Message;

/**
 * Template pattern for a request as approval/denial end in the request being deleted from the appropriate repository.
 */
public abstract class Request
    implements I_MessageSender, I_RepositoryItem< Request > {

    protected RequestType _type;

    /**
     *
     * @param type
     */
    public Request(RequestType type){
        _type = type;
    }


    /**
     * Stores the object in it's respective repository.
     *
     * @return the object that has been added to the repository.
     */
    @Override
    public Request include(){
        RequestRepository.getInstance().add(this);
        return this;
    }

    /**
     *
     * @return
     */
    public RequestType getType(){
        return _type;
    }


    /**
     * Approves the request.
     */
    public final void approve() throws Exception {
        approveAction();
        destroy();
    }

    /**
     * Denies the request and thus deletes it.
     */
    public final void deny() throws Exception{
        denyAction();
        destroy();
    }

    /**
     * The action following request approval.
     */
    protected abstract void approveAction() throws Exception;

    /**
     * The action following request denial.
     */
    protected abstract void denyAction();

    /**
     * Deletes the current request from the repo.
     *
     * This can be overridden but it is not necessary.
     */
    protected void destroy(){
        RequestRepository.getInstance().remove(this);
    }

    /**
     * Sends a message to another user.
     *
     * @param recipient the target user.
     * @param message the message to be sent.
     */
    @Override
    public final void sendMessage(I_MessageRecipient recipient, String message){
        recipient.addMessage(new Message(this, message));
    }
}
