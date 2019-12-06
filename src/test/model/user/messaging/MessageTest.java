package test.model.user.messaging;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.model.user.messaging.*;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    private static I_MessageSender _sender;
    private static I_MessageRecipient _recipient;


    @BeforeAll
    public static void setUpClass() {
        _sender = new DummyMessageSender();
        _recipient = new DummyMessageRecipient();
    }

    @Test
    @DisplayName("sendMessage")
    void sendMessage() {
        Message message = new Message("This is a test.");
        _sender.sendMessage(_recipient, message);

        assertTrue(_recipient.getMessages().contains(message));
    }
}