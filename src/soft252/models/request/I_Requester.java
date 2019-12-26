package soft252.models.request;

/**
 * An interface that indicates a class creates requests. This is simply an identifier.
 *
 * NOTE: The reason as to why the body of this interface is empty is actually quite a complicated problem. There is no
 * one single format for a request because each request requires different data; therefore there cannot be a single
 * signature that ever class that implements this interface can follow.
 * @param <T> the type of request.
 */
public interface I_Requester<T extends Request>{}