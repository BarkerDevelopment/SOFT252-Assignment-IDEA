package soft252.model.user.feedback;

import java.time.LocalDateTime;

/**
 * Defines the functions for an object that is used for feedback.
 */
public interface I_Feedback extends I_Moderated{
    /**
     * @return the _dateTime variable. This represents the DateTime the feedback was sent.
     */
    public abstract LocalDateTime getDateTime();

    /**
     * @return the _feedback variable. This represents the contents of the feedback that was sent.
     */
    public abstract String getFeedback();

    /**
     * @param feedback the new contents of the _feedback variable.
     */
    public abstract void setFeedback(String feedback);
}
