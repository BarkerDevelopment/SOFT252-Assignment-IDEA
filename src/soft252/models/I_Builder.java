package soft252.models;

/**
 * Interface for the builder pattern.
 * @param <T> the class of the object being created by the builder. This should be the class that the builder is within.
 */
public interface I_Builder<T> {
    /**
     * Builds the final object and returns it.
     * @return the object.
     */
    public abstract T build();
}
