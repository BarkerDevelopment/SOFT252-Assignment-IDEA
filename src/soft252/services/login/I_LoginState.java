package soft252.services.login;

/**
 * A state for the LoginHandler.
 */
public interface I_LoginState {
    /**
     * An attempt to login.
     *
     * @param handler the LoginHandler.
     */
    public abstract void login(LoginHandler handler);

    /**
     * An attempt to logout.
     *
     * @param handler the LoginHandler.
     */
    public abstract void logout(LoginHandler handler);
}
