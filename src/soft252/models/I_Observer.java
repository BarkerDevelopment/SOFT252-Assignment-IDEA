package soft252.models;

/**
 * One half of the Observer pattern. Classes that implement this interface will observe I_Observable objects.
 *
 * @param <T> type of data that Observable will publish.
 */
public interface I_Observer<T> {
    /**
     * @param item the updated value.
     */
    public abstract void update(T item);
}
