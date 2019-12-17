package test.model.user.feedback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.exceptions.OutOfRangeException;
import soft252.model.user.feedback.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackModeratorTest {
    private static I_FeedbackModerator _moderator;
    private static I_FeedbackRecipient _recipient;

    private static ArrayList< I_Feedback > _feedback;
    private static ArrayList< I_Feedback > _moderatedFeedback;
    private static ArrayList< I_Feedback > _unmoderatedFeedback;

    @BeforeEach
    void setUp() {
        FeedbackFactory f = new FeedbackFactory();
        _moderator = new DummyFeedbackModerator();

        _moderatedFeedback = new ArrayList<>();
        _unmoderatedFeedback = new ArrayList<>();

        try {
            _moderatedFeedback.add(f.create("Moderated Feedback without rating."));
            _moderatedFeedback.add(f.create("Moderated Feedback with rating.", 5));
            _unmoderatedFeedback.add(f.create("Un-moderated Feedback without rating."));
            _unmoderatedFeedback.add(f.create("Un-moderated Feedback with rating.", 5));
        } catch (OutOfRangeException e) {
            // This won't be triggered because the rating passed in is between the limits of the ratings.
            e.printStackTrace();
        }

        for (I_Feedback feedback: _moderatedFeedback) feedback.moderate();

        _feedback = new ArrayList<>();
        _feedback.addAll(_moderatedFeedback);
        _feedback.addAll(_unmoderatedFeedback);

        _recipient = new DummyFeedbackRecipient();
        for (I_Feedback feedback : _feedback) _recipient.addFeedback(feedback);
    }

    @Test
    @DisplayName("getRecipientFeedback")
    void getRecipientFeedback() {
        ArrayList< I_Feedback > expResult = _feedback;
        ArrayList< I_Feedback > result = _moderator.getRecipientFeedback(_recipient);

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("getUnmoderatedFeedback")
    void getUnmoderatedFeedback() {
        ArrayList< I_Feedback > expResult = _unmoderatedFeedback;
        ArrayList< I_Feedback > result = _moderator.getUnmoderatedFeedback(_recipient);

        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("moderateFeedback")
    void moderateFeedback() {
        _moderator.moderateFeedback(_unmoderatedFeedback.get(0));
        assertTrue(_recipient.getFeedback().get(2).isModerated());

        _moderator.moderateFeedback(_unmoderatedFeedback.get(1));
        assertTrue(_recipient.getFeedback().get(3).isModerated());
    }

    @Test
    @DisplayName("moderateAndEditFeedback")
    void testModerateFeedback() {
        ArrayList< I_Feedback > unmoderatedFeedback = _recipient.getFeedback(false);

        I_Feedback feedback = unmoderatedFeedback.get(0);
        _moderator.moderateFeedback(feedback, "Un-moderated Feedback without rating. Edited");
        assertTrue(_recipient.getFeedback().get(2).isModerated());
        assertNotEquals("Un-moderated Feedback without rating.", _recipient.getFeedback().get(2).getFeedback());

        feedback = unmoderatedFeedback.get(1);
        _moderator.moderateFeedback(feedback, "Un-moderated Feedback with rating. Edited");
        assertTrue(_recipient.getFeedback().get(3).isModerated());
        assertNotEquals("Un-moderated Feedback with rating.", _recipient.getFeedback().get(3).getFeedback());
    }

    @Test
    @DisplayName("deleteFeedback")
    void deleteFeedback() {
        for (I_Feedback feedback: _feedback){
            _moderator.deleteFeedback(_recipient, feedback);
            assertFalse(_recipient.getFeedback().contains(feedback));
        }
    }
}