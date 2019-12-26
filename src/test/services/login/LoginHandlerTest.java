package test.services.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.models.users.Patient;
import soft252.models.users.UserRepository;
import soft252.models.users.info.Gender;
import soft252.services.login.LoggedInState;
import soft252.services.login.LoggedOutState;
import soft252.services.login.LoginHandler;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class LoginHandlerTest {
    private LoginHandler _handler;

    @BeforeEach
    void setUp() {
        _handler = LoginHandler.getInstance();
        new Patient("1000", "John", "Cena", Gender.MALE).include();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("login - while logged out")
    void login() {
        _handler.setUsername("P1000");
        _handler.setPassword("password");

        _handler.login();

        assertTrue(_handler.getState() instanceof LoggedInState);
    }

    @Test
    @DisplayName("login - while logged in")
    void login2() {
        _handler.setState(new LoggedInState(UserRepository.getInstance().get("P1000")));

        _handler.setUsername("P1000");
        _handler.setPassword("password");

        _handler.login();

        assertTrue(_handler.getState() instanceof LoggedInState);
    }

    @Test
    @DisplayName("logout - while logged in")
    void logout() {
        _handler.setState(new LoggedInState(UserRepository.getInstance().get("P1000")));

        _handler.logout();

        assertTrue(_handler.getState() instanceof LoggedOutState);
    }

    @Test
    @DisplayName("logout - while logged out")
    void logout2() {
        _handler.logout();

        assertTrue(_handler.getState() instanceof LoggedOutState);
    }
}