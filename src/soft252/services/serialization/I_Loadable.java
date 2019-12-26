package soft252.services.serialization;

/**
 * Interface that identifies that a class can have information loaded about it.
 */
public interface I_Loadable {
    /**
     * Load stored information.
     */
    public abstract void load();
}
