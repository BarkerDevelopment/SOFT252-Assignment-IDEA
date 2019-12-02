package soft252.model.user.feedback;

import java.util.ArrayList;

/**
 * Defines the functions of an object that receives feedback.
 */
public interface I_FeedbackRecipient {
    /**
     * NOTE: This should not be used by the recipient themselves. This function's intended to be used by the
     * I_FeedbackModerator.
     *
     * @return an ArrayList of all feedback.
     */
    public abstract ArrayList<I_Feedback> getFeedback();

    /**
     * Returns the feedback based on whether it is moderated or not.
     *
     * @param flag if TRUE returns all moderated feedback, if FALSE returns all un-moderated feedback.
     * @return an ArrayList of all moderated feedback.
     */
    public abstract ArrayList<I_Feedback> getFeedback(boolean flag);

    /**
     * Adds a piece of feedback.
     *
     * @param feedback the new feedback.
     */
    public abstract void addFeedback(I_Feedback feedback);

    /**
     * Removes a piece of feedback.
     *
     * @param feedback the target feedback.
     */
    public abstract void removeFeedback(I_Feedback feedback);
}
