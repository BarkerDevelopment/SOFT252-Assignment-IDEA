package soft252.models.users;

import soft252.models.I_Repository;
import soft252.models.drugs.Drug;
import soft252.models.request.AccountTerminationRequest;
import soft252.models.request.I_Requester;
import soft252.models.users.info.Role;
import soft252.services.serialization.I_SerializationHandler;
import soft252.services.serialization.RepositoryDeserializationHandler;
import soft252.services.serialization.RepositorySerializationHandler;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * A repository of all the users in the system.
 */
public class UserRepository
        implements I_Repository<User>, I_Requester< AccountTerminationRequest > {

    private static String FILE_NAME = "users";
    private final I_SerializationHandler _serializationHandler;
    private final I_SerializationHandler _deserializationHandler;

    private static UserRepository INSTANCE;
    private final EnumMap<Role, ArrayList<User>> _users;

    /**
     * Singleton constructor.
     */
    private UserRepository(){
        _serializationHandler = new RepositorySerializationHandler(FILE_NAME, this);
        _deserializationHandler = new RepositoryDeserializationHandler(FILE_NAME, this);

        _users = new EnumMap<>(Role.class);
        clear();
    }

    /**
     * UserRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static UserRepository getInstance() {
        if(INSTANCE == null) INSTANCE = new UserRepository();

        return INSTANCE;
    }

    /**
     * Wipes the contents of the repository and replaces it with the contents of the object passed. Primarily used by
     * RepositoryDeserializationHandler.
     *
     * @param objects the object the RepositoryDeserializationHandler passes.
     */
    @Override
    public void initialise(Object objects) {
        clear();
        ArrayList< User > users = (ArrayList< User >) objects;

        addAll(users);
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
     * Adds a collection of items to the repository.
     *
     * @param items the new items.
     */
    @Override
    public void addAll(ArrayList< User > items) {

    }

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    @Override
    public void remove(User item) {
        new AccountTerminationRequest(item).include();
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
        for(Role r: Role.values()) _users.put(r, new ArrayList<>());
    }

    /**
     * Load stored information.
     */
    @Override
    public void load() {
        _deserializationHandler.action();
    }

    /**
     * Save stored information.
     */
    @Override
    public void save() {
        _serializationHandler.action();
    }
}
