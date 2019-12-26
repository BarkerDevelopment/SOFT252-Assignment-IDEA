package soft252.models.request;

import soft252.models.I_Repository;
import soft252.services.serialization.I_SerializationHandler;
import soft252.services.serialization.RepositoryDeserializationHandler;
import soft252.services.serialization.RepositorySerializationHandler;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * A repository of all the requests in the system.
 */
public class RequestRepository
        implements I_Repository< Request > {

    private static String FILE_NAME = "requests";
    private final I_SerializationHandler _serializationHandler;
    private final I_SerializationHandler _deserializationHandler;

    private static RequestRepository INSTANCE;
    private final EnumMap< RequestType, ArrayList< Request > > _requests;

    /**
     * Singleton constructor.
     */
    private RequestRepository(){
        _serializationHandler = new RepositorySerializationHandler(FILE_NAME, this);
        _deserializationHandler = new RepositoryDeserializationHandler(FILE_NAME, this);

        _requests = new EnumMap<>(RequestType.class);
        clear();
    }

    /**
     * UserRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static RequestRepository getInstance() {
        if(INSTANCE == null) INSTANCE = new RequestRepository();

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
        ArrayList< Request > requests = (ArrayList< Request >) objects;

        addAll(requests);
    }

    /**
     * @return the list of all items in the repo.
     */
    @Override
    public ArrayList< Request > get() {
        ArrayList< Request > allRequests = new ArrayList<>();

        for(RequestType r : RequestType.values()) allRequests.addAll(_requests.get(r));

        return allRequests;
    }

    /**
     * Gets all requests of the passed type.
     *
     * @param type the queried type.
     * @return the requests of the specified type.
     */
    public ArrayList<Request> get(RequestType type) {
        return _requests.get(type);
    }

    /**
     * Adds an item to the repository.
     *
     * @param item the new item.
     */
    @Override
    public void add(Request item) {
        _requests.get(item.getType()).add(item);
    }

    /**
     * Adds a collection of items to the repository.
     *
     * @param items the new items.
     */
    @Override
    public void addAll(ArrayList< Request > items) {

    }

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    @Override
    public void remove(Request item) {
        if(contains(item)) _requests.get(item.getType()).remove(item);
    }

    /**
     * Checks if the repository contains the item.
     *
     * @param item the target item.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    @Override
    public boolean contains(Request item) {
        return _requests.get(item.getType()).contains(item);
    }

    /**
     * Clears the repo of all items. This is mainly for testing purposes.
     */
    @Override
    public void clear() {
        for(RequestType r: RequestType.values()) _requests.put(r, new ArrayList<>());
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
