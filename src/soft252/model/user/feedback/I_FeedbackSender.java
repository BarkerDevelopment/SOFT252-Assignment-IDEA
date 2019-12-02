package soft252.model.user.feedback;

/**
 * Defines the functions of an object that sends feedback.
 */
public interface I_FeedbackSender {
    /**
     * Sends a piece of feedback to a target.
     *
     * @param recipient the target recipient.
     * @param feedback the new feedback.
     */
    public abstract void giveFeedback(I_FeedbackRecipient recipient, I_Feedback feedback);
}
