package test.services.serialization;

import soft252.models.I_Repository;
import soft252.services.serialization.I_SerializationHandler;
import soft252.services.serialization.RepositoryDeserializationHandler;
import soft252.services.serialization.RepositorySerializationHandler;

import java.util.ArrayList;

public class DummyRepository implements I_Repository< DummyObject > {
    private static String FILE_NAME = "dummies";
    private static DummyRepository INSTANCE;
    private final I_SerializationHandler _serializationHandler;
    private final I_SerializationHandler _deserializationHandler;
    private final ArrayList< DummyObject > _dummies;

    /**
     * Singleton constructor.
     */
    public DummyRepository() {
        _serializationHandler = new RepositorySerializationHandler( FILE_NAME, this);
        _deserializationHandler = new RepositoryDeserializationHandler(FILE_NAME, this);
        _dummies = new ArrayList<>();
    }

    /**
     * UserRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static DummyRepository getInstance() {
        if (INSTANCE == null) INSTANCE = new DummyRepository();

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
        ArrayList< DummyObject > dummies = (ArrayList< DummyObject >) objects;

        addAll(dummies);
    }

    /**
     * @return the list of all items in the repo.
     */
    @Override
    public ArrayList< DummyObject > get() {
        return _dummies;
    }

    /**
     * Adds an item to the repository.
     *
     * @param item the new item.
     */
    @Override
    public void add(DummyObject item) {
        _dummies.add(item);
    }

    /**
     * Adds a collection of items to the repository.
     *
     * @param items the new items.
     */
    @Override
    public void addAll(ArrayList< DummyObject > items) {
        _dummies.addAll(items);
    }

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    @Override
    public void remove(DummyObject item) {
        _dummies.remove(item);
    }

    /**
     * Checks if the repository contains the item.
     *
     * @param item the target item.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    @Override
    public boolean contains(DummyObject item) {
        return _dummies.contains(item);
    }

    /**
     * Clears the repo of all items. This is mainly for testing purposes.
     */
    @Override
    public void clear() {
        _dummies.clear();
    }

    /**
     *
     */
    @Override
    public void load() {
        _deserializationHandler.action();
    }

    /**
     *
     */
    @Override
    public void save() {
        _serializationHandler.action();
    }
}

