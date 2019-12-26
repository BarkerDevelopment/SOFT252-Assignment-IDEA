package test.models.users.feedback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.exceptions.OutOfRangeException;
import soft252.models.users.feedback.FeedbackFactory;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackFactoryTest {
    private static FeedbackFactory _f;

    @BeforeEach
    void setup(){
        _f = new FeedbackFactory();
    }

    @Test
    @DisplayName("create")
    void create() throws OutOfRangeException {
        for (int i = FeedbackFactory.MIN_RATING; i < FeedbackFactory.MAX_RATING + 1; i++) {
            int finalI = i; // Lambda requires final variable.
            assertDoesNotThrow(() -> _f.create("This is a test.", finalI));
        }

        assertThrows(OutOfRangeException.class, () -> _f.create("This is a test.", 0));
        assertThrows(OutOfRangeException.class, () -> _f.create("This is a test.", 11));
    }
}