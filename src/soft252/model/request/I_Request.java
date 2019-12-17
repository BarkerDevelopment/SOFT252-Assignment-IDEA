package soft252.model.request;

/**
 * Defines the functions for a request.
 */
public interface I_Request {
    /**
     * Approves the request.
     */
    public abstract void approve();

    /**
     * Denies the request and thus deletes it.
     */
    public abstract void deny();
}
