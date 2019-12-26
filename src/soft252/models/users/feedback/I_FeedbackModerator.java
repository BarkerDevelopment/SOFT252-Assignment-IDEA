package soft252.models.users.feedback;

import java.util.ArrayList;

/**
 * Defines the functions of an object that moderates feedback.
 */
public interface I_FeedbackModerator {
    /**
     * Retrieves all the feedback from the recipient.
     *
     * @param recipient the target recipient.
     * @return an ArrayList of all feedback.
     */
    public abstract ArrayList<I_Feedback> getRecipientFeedback(I_FeedbackRecipient recipient);

    /**
     * Retrieves all the feedback from the recipient that isn't moderated.
     *
     * @param recipient the target recipient.
     * @return an ArrayList of all un-moderated feedback.
     */
    public abstract ArrayList<I_Feedback> getUnmoderatedFeedback(I_FeedbackRecipient recipient);

    /**
     * Confirms the feedback has been moderated.
     *
     * @param feedback the target feedback.
     */
    public abstract void moderateFeedback(I_Feedback feedback);

    /**
     * Edits the target feedback with a new feedback string, then confirms it's been moderated.
     *
     * @param feedback the target feedback.
     * @param string the new feedback string.
     */
    public abstract void moderateFeedback(I_Feedback feedback, String string);

    /**
     * Deletes a target feedback from a target recipient.
     *
     * @param recipient the target recipient.
     * @param feedback the target feedback.
     */
    public abstract void deleteFeedback(I_FeedbackRecipient recipient, I_Feedback feedback);
}

