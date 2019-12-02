package soft252.model;

import java.util.ArrayList;

/**
 * A class that stores instances of objects for a centralised access point to them.
 *
 * @param <T> the type of object to be stored.
 */
public interface I_Repository<T> {

    /**
     * @return the list of all items in the repo.
     */
    public abstract ArrayList<T> getAll();

    /**
     * Adds an item to the repository.
     *
     * @param item the new item.
     */
    public abstract void add(T item);

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    public abstract void remove(T item);

    /**
     * Checks if the repository contains the item.
     *
     * @param item the target item.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    public abstract boolean contains(T item);
}
