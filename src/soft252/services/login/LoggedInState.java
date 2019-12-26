package soft252.services.login;

import soft252.models.users.User;

/**
 *
 */
public class LoggedInState implements I_LoginState {
    private final User _user;

    /**
     *
     * @param user
     */
    public LoggedInState(User user){
        _user = user;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return _user;
    }

    /**
     * An attempt to login.
     *
     * @param handler the LoginHandler.
     */
    @Override
    public void login(LoginHandler handler) {
        handler.setState(this);
    }

    /**
     * An attempt to logout.
     *
     * @param handler the LoginHandler
     */
    @Override
    public void logout(LoginHandler handler) {
        handler.setState(new LoggedOutState());
    }
}
