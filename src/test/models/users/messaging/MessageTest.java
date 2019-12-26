package test.models.users.messaging;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.models.users.messaging.*;

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
        Message message = new Message(_sender, "This is a test.");

        _sender.sendMessage(_recipient, "This is a test.");

        assertTrue(
                _recipient.getMessages().get(0).getMessage().equals(message.getMessage()) &&
                        _recipient.getMessages().get(0).getSender().equals(message.getSender())
                );
    }
}