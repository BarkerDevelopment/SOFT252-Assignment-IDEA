package soft252.model.user.feedback;

import java.time.LocalDateTime;

/**
 * Generic feedback class.
 */
public class Feedback
    implements I_Feedback{

    private final LocalDateTime _dateTime;
    private String _feedback;
    private Boolean _isModerated;

    /**
     * Creates a feedback object.
     *
     * @param feedback the feedback string.
     */
    public Feedback(String feedback){
        _dateTime = LocalDateTime.now();
        _feedback = feedback;
        _isModerated = false;
    }

    /**
     * @return the _dateTime variable. This represents the DateTime the feedback was sent.
     */
    @Override
    public LocalDateTime getDateTime() {
        return _dateTime;
    }

    /**
     * @return the _feedback variable. This represents the contents of the feedback that was sent.
     */
    @Override
    public String getFeedback() {
        return _feedback;
    }

    /**
     * @param feedback the new contents of the _feedback variable.
     */
    @Override
    public void setFeedback(String feedback) {
        _feedback = feedback;
    }

    /**
     * @return the _isModerated variable. This represents if object has been moderated.
     */
    @Override
    public boolean isModerated() {
        return _isModerated;
    }

    /**
     * @param flag the value to set _isModerated flag to.
     */
    @Override
    public void setIsModerated(Boolean flag) {
        _isModerated = flag;
    }

    /**
     * @return the new state of the _isModerated variable.
     */
    @Override
    public boolean toggleIsModerated() {
        _isModerated = ! _isModerated;

        return _isModerated;
    }
}
