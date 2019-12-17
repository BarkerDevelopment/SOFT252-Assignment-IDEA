package test.model.user.feedback;

import soft252.model.user.feedback.I_Feedback;
import soft252.model.user.feedback.I_FeedbackModerator;
import soft252.model.user.feedback.I_FeedbackRecipient;

import java.util.ArrayList;

/**
 * A test class for testing feedback moderation. The code in this class is copied directly from the Admin class as this
 * class emulates Admin without the complication of the other functions.
 */
public class DummyFeedbackModerator implements I_FeedbackModerator {
    /**
     * Retrieves all the feedback from the recipient.
     *
     * @param recipient the target recipient.
     * @return an ArrayList of all feedback.
     */
    @Override
    public ArrayList< I_Feedback > getRecipientFeedback(I_FeedbackRecipient recipient) {
        return recipient.getFeedback();
    }

    /**
     * Retrieves all the feedback from the recipient that isn't moderated.
     *
     * @param recipient the target recipient.
     * @return an ArrayList of all un-moderated feedback.
     */
    @Override
    public ArrayList< I_Feedback > getUnmoderatedFeedback(I_FeedbackRecipient recipient) {
        return recipient.getFeedback(false);
    }

    /**
     * Confirms the feedback has been moderated.
     *
     * @param feedback the target feedback.
     */
    @Override
    public void moderateFeedback(I_Feedback feedback) {
        feedback.moderate();
    }

    /**
     * Edits the target feedback with a new feedback string, then confirms it's been moderated.
     *
     * @param feedback the target feedback.
     * @param string   the new feedback string.
     */
    @Override
    public void moderateFeedback(I_Feedback feedback, String string) {
        feedback.setFeedback(string);
        feedback.moderate();
    }

    /**
     * Deletes a target feedback from a target recipient.
     *
     * @param recipient the target recipient.
     * @param feedback  the target feedback.
     */
    @Override
    public void deleteFeedback(I_FeedbackRecipient recipient, I_Feedback feedback) {
        recipient.removeFeedback(feedback);
    }
}
