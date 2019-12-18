package soft252.model;

/**
 * One half of the Observer pattern. I_Observers will observe classes that implement this interface.
 */
public interface I_Observable< T > {
    /**
     * Subscribes an o to the observable.
     *
     * @param o the o wishing to observe the observable.
     */
    public abstract void subscribe(I_Observer< T > o);

    /**
     * Unsubscribes an o from the observable.
     *
     * @param o the o wishing to stop observing the observable.
     */
    public abstract void unsubscribe(I_Observer< T > o);

    /**
     * Notifies all observers.
     */
    public abstract void publish();
}
