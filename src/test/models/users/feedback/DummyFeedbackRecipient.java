package test.models.users.feedback;

import soft252.models.users.feedback.I_Feedback;
import soft252.models.users.feedback.I_FeedbackRecipient;

import java.util.ArrayList;

/**
 * A test class for feedback receiving. The code in this class is copied directly from the Doctor class as this
 *  * class emulates Doctor without the complication of the other functions.
 */
public class DummyFeedbackRecipient implements I_FeedbackRecipient {
    private ArrayList< I_Feedback > _feedback;

    /**
     *
     */
    public DummyFeedbackRecipient(){
        _feedback = new ArrayList<>();
    }

    /**
     * NOTE: This should not be used by the recipient themselves. This function's intended to be used by the
     * I_FeedbackModerator.
     *
     * @return an ArrayList of all feedback.
     */
    @Override
    public ArrayList< I_Feedback > getFeedback() {
        return _feedback;
    }

    /**
     * Returns the feedback based on whether it is moderated or not.
     *
     * @param flag if TRUE returns all moderated feedback, if FALSE returns all un-moderated feedback.
     * @return an ArrayList of all moderated feedback.
     */
    @Override
    public ArrayList< I_Feedback > getFeedback(boolean flag) {
        ArrayList<I_Feedback> allFeedback = new ArrayList<>();

        for(I_Feedback feedback: _feedback) if(feedback.isModerated() == flag) allFeedback.add(feedback);

        return allFeedback;
    }

    /**
     * Adds a piece of feedback.
     *
     * @param feedback the new feedback.
     */
    @Override
    public void addFeedback(I_Feedback feedback) {
        _feedback.add(feedback);
    }

    /**
     * Removes a piece of feedback.
     *
     * @param feedback the target feedback.
     */
    @Override
    public void removeFeedback(I_Feedback feedback) {
        _feedback.remove(feedback);
    }
}
