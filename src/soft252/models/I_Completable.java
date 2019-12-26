package soft252.models;

/**
 * Defines the functions for an object that is completable.
 */
public interface I_Completable {
    /**
     * @return the _isCompletable variable. This represents if object has been completed.
     */
    public abstract boolean isCompleted();

    /**
     * Completes a completable object.
     */
    public abstract void complete();
}
