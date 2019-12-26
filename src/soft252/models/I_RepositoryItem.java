package soft252.models;

/**
 * This interface implies that the class is stored in a repository.
 *
 * @param <T> the class of the object which inherits the interface.
 */
public interface I_RepositoryItem<T> {
    /**
     * Stores the object in it's respective repository.
     *
     * @return the object that has been added to the repository.
     */
    public abstract T include();
}
