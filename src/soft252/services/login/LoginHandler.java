package soft252.services.login;

/**
 *
 */
public class LoginHandler {
    private static LoginHandler INSTANCE;

    private I_LoginState _state;
    private String _username;
    private String _password;

    /**
     * Singleton constructor.
     */
    private LoginHandler(){
        _state = new LoggedOutState();
    }

    /**
     * UserRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static LoginHandler getInstance() {
        if(INSTANCE == null) INSTANCE = new LoginHandler();

        return INSTANCE;
    }

    /**
     * @return the _username variable.
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @return the _password variable.
     */
    public String getPassword() {
        return _password;

    }

    /**
     * @return the state of the handler.
     */
    public I_LoginState getState() {
        return _state;
    }

    /**
     * @param username sets the _username variable to a new value.
     */
    public void setUsername(String username) {
        _username = username;
    }

    /**
     * @param password sets the _password variable to a new value.
     */
    public void setPassword(String password) {
        _password = password;
    }

    /**
     * @param state changes the state of the LoginHandler.
     */
    public void setState(I_LoginState state) {
        _state = state;
    }

    /**
     *
     */
    public void login(){
        _state.login(this);
    }

    /**
     *
     */
    public void logout(){
        _state.logout(this);
    }
}
