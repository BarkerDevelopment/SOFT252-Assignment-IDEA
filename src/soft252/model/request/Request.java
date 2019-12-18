package soft252.model.request;

/**
 * Template pattern for a request as approval/denial end in the request being deleted from the appropriate repository.
 */
public abstract class Request {
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
    public final void deny(){
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
}
