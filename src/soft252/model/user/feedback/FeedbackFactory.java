package soft252.model.user.feedback;

import soft252.exceptions.OutOfRangeException;

/**
 * A class that creates I_Feedback objects.
 */
public class FeedbackFactory {

    private static final int _ratingMin = 0;
    private static final int _ratingMax = 10;

    /**
     * Creates a feedback object.
     *
     * @param string the feedback string.
     * @return the feedback object. This is of type Feedback.
     */
    public I_Feedback create(String string) {
        return new Feedback(string);
    }

    /**
     * Creates a feedback object. If the rating is null, it creates a Feedback object where as if the rating is an
     * positive integer, it creates a FeedbackWithRating object.
     *
     * @param string the feedback string.
     * @param rating the feedback rating.
     * @return the feedback object.
     * @throws OutOfRangeException if rating is out of the specified range.
     */
    public I_Feedback create(String string, Integer rating) throws OutOfRangeException {

        if(rating == null){
            return new Feedback(string);

        }else{
            if(rating > _ratingMin && rating < _ratingMax){
                return new FeedbackWithRating(string, rating);

            }else{
                throw new OutOfRangeException(String.format("Feedback rating must be between %d and %d.", _ratingMin, _ratingMax));
            }

        }
    }
}

