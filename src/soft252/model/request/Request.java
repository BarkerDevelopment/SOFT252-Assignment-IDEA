package soft252.model.request;

/**
 * Template pattern for a request as approval/denial end in the request being deleted from the appropriate repository.
 */
public abstract class Request {
    /**
     * Approves the request.
     */
    public final void approve(){
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
    protected abstract void approveAction();

    /**
     * The action following request denial.
     */
    protected abstract void denyAction();

    /**
     * Deletes the current request from the repo.
     */
    private void destroy(){
        RequestRepository.getInstance().remove(this);
    }
}
