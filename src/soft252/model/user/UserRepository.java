package soft252.model.user;

import soft252.model.I_Repository;
import soft252.model.request.AccountTerminationRequest;
import soft252.model.request.I_Requester;
import soft252.model.user.info.Role;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * A repository of all the users in the system.
 */
public class UserRepository
        implements I_Repository<User>, I_Requester< AccountTerminationRequest > {

    private static UserRepository _instance;
    private final EnumMap<Role, ArrayList<User>> _users;

    /**
     * Singleton constructor.
     */
    private UserRepository(){
        _users = new EnumMap<>(Role.class);

        for(Role r: Role.values()) _users.put(r, new ArrayList<>());
    }

    /**
     * UserRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static UserRepository getInstance() {
        if(_instance == null) _instance = new UserRepository();

        return _instance;
    }

    /**
     * @return the list of all items in the repo.
     */
    @Override
    public ArrayList<User> get() {
        ArrayList<User> allUsers = new ArrayList<>();

        for(Role r : Role.values()) allUsers.addAll(_users.get(r));

        return allUsers;
    }


    /**
     * Gets all users of the passed role.
     *
     * @param role the queried role.
     * @return the users of the specified role.
     */
    public ArrayList<User> get(Role role) {
        return _users.get(role);
    }

    /**
     * Finds the user of the passed in ID.
     *
     * @param id the search ID.
     * @return the User with the entered ID. Will return NULL if no User is found.
     */
    public User get(String id){
        Role userRole = Role.fromString(id.charAt(0));

        for(User user : get(userRole)) if(user.getId().toString().equals(id)) return user;

        return null; // No User has the ID, therefore return NULL.
    }

    /**
     * Adds an item to the repository.
     *
     * @param item the new item.
     */
    @Override
    public void add(User item) {
        if(!contains(item)) _users.get(item.getId().getRole()).add(item);
    }

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    @Override
    public void remove(User item) {
        new AccountTerminationRequest(item);
    }

    /**
     * Checks if the repository contains the item.
     *
     * @param item the target item.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    @Override
    public boolean contains(User item) {
        return _users.get(item.getId().getRole()).contains(item);
    }

    /**
     * Checks if the repository contains a user based on their ID.
     *
     * @param id the User ID.
     * @return TRUE if repository contains the user, otherwise FALSE.
     */
    public boolean contains(String id){
        return get(id) != null;
    }

    /**
     * Clears the repo of all items. This is mainly for testing purposes.
     */
    @Override
    public void clear() {
        _users.clear();
    }
}
