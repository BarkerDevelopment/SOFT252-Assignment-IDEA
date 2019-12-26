package soft252.services.serialization;

/**
 * Outlines shared functions between SerializationHandler. This enforces the Template Pattern.
 */
public interface I_SerializationHandler {
    /**
     * Performs the SerializationHandler's action.
     */
    public abstract void action();
}

