package soft252.services.serialization;

import soft252.models.I_Repository;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * A class that handles a Repository's deserialization.
 */
public class RepositoryDeserializationHandler
        implements I_SerializationHandler{
    private String _filename;
    private I_Repository< ? > _repo;

    /**
     * Default constructor.
     *
     * @param filename the destination file name.
     * @param repo the target repository.
     */
    public RepositoryDeserializationHandler(String filename, I_Repository< ? > repo) {
        _filename = filename;
        _repo = repo;
    }

    /**
     * Performs the SerializationHandler's action.
     *
     * Loads all items within the repository.
     */
    @Override
    public void action() {
        try
        {
            FileInputStream file = new FileInputStream(_filename);
            ObjectInputStream in = new ObjectInputStream(file);

            Object objects = in.readObject();

            in.close();
            file.close();

            _repo.initialise(objects);
        }
        catch(IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
