package soft252.model.user.feedback;

/**
 * Defines the functions for an object that is moderated.
 */
public interface I_Moderated {
    /**
     * @return the _isModerated variable. This represents if object has been moderated.
     */
    public abstract boolean isModerated();

    /**
     * Moderates a moderatable object.
     */
    public abstract void moderate();
}
