package test.models.users.feedback;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.models.users.feedback.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FeedbackTest {
    private static FeedbackFactory _f;
    private static I_FeedbackSender _sender;
    private static I_FeedbackRecipient _recipient;

    @BeforeAll
    static void setUp() {
        _f = new FeedbackFactory();
        _sender = new DummyFeedbackSender();
        _recipient = new DummyFeedbackRecipient();
    }

    @Test
    @DisplayName("sendFeedback")
    void giveFeedback() {
        I_Feedback feedback = _f.create("This is a test.");
        _sender.giveFeedback(_recipient, feedback);

        assertTrue(_recipient.getFeedback().contains(feedback));
    }
}