package soft252.model.request;

import soft252.model.I_Repository;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * A repository of all the requests in the system.
 */
public class RequestRepository
        implements I_Repository< Request > {
    private static RequestRepository _instance;
    private final EnumMap< RequestType, ArrayList< Request > > _requests;

    /**
     * Singleton constructor.
     */
    private RequestRepository(){
        _requests = new EnumMap<>(RequestType.class);

        for(RequestType r: RequestType.values()) _requests.put(r, new ArrayList<>());
    }

    /**
     * UserRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static RequestRepository getInstance() {
        if(_instance == null) _instance = new RequestRepository();

        return _instance;
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
        _requests.clear();
    }
}
