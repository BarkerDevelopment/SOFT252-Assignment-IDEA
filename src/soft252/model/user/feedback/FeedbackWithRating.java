package soft252.model.user.feedback;

/**
 * Feedback subclass that includes a rating.
 */
public class FeedbackWithRating extends Feedback{
    private final int _rating;
    private Boolean _isModerated;

    /**
     * Creates a feedback object with a rating.
     *
     * @param feedback the feedback string.
     * @param rating the feedback rating.
     */
    public FeedbackWithRating(String feedback, int rating){
        super(feedback);
        _rating = rating;
        _isModerated = false;
    }

    /**
     * @return the _rating variable. This represents the rating associated with the feedback.
     */
    public int getRating() {
        return _rating;
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
