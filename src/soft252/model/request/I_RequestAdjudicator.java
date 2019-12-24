package soft252.model.request;

/**
 * An interface that indicates an object approves or declines requests.
 */
public interface I_RequestAdjudicator {
    /**
     * Approve the passed request.
     *
     * @param request the target request.
     */
    public abstract boolean approveRequest(Request request);

    /**
     * Defines the passed request.
     *
     * @param request the target request.
     */
    public abstract boolean declineRequest(Request request);
}
