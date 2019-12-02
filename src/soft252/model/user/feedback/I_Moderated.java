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
     * @param flag the value to set _isModerated flag to.
     */
    public abstract void setIsModerated(Boolean flag);

    /**
     * @return the new state of the _isModerated variable.
     */
    public abstract boolean toggleIsModerated();
}
