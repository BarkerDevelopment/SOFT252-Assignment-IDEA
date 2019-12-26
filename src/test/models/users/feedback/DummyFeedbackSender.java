package test.models.users.feedback;

import soft252.models.users.feedback.I_Feedback;
import soft252.models.users.feedback.I_FeedbackRecipient;
import soft252.models.users.feedback.I_FeedbackSender;

/**
 * A test class for feedback sending. The code in this class is copied directly from the Patient class as this
 *  * class emulates Patient without the complication of the other functions.
 */
public class DummyFeedbackSender implements I_FeedbackSender {
    /**
     * Sends a piece of feedback to a target.
     *
     * @param recipient the target recipient.
     * @param feedback  the new feedback.
     */
    @Override
    public void giveFeedback(I_FeedbackRecipient recipient, I_Feedback feedback) {
        recipient.addFeedback(feedback);
    }
}
