package soft252.services.login;

import soft252.models.users.User;
import soft252.models.users.UserRepository;

public class LoggedOutState implements I_LoginState {
    /**
     * An attempt to login.
     *
     * @param handler the LoginHandler.
     */
    @Override
    public void login(LoginHandler handler) {
        UserRepository repo = UserRepository.getInstance();

        if(repo.contains(handler.getUsername())){
            User user = repo.get(handler.getUsername());

            if(user.getPassword() == (handler.getPassword().hashCode())){
                handler.setState(new LoggedInState(user));

            }else{
                handler.setState(this);
            }

        }else{
            handler.setState(this);

        }
    }

    /**
     * An attempt to logout.
     *
     * @param handler the LoginHandler.
     */
    @Override
    public void logout(LoginHandler handler) {
        handler.setState(this);
    }
}
