package soft252.services.serialization;

import soft252.models.I_Repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * A class that handles a Repository's serialization.
 */
public class RepositorySerializationHandler
        implements I_SerializationHandler {
    private String _filename;
    private I_Repository< ? > _repo;

    /**
     * Default constructor.
     *
     * @param filename the destination file name.
     * @param repo the target repository.
     */
    public RepositorySerializationHandler(String filename, I_Repository< ? > repo) {
        _filename = filename;
        _repo = repo;
    }

    /**
     * Performs the SerializationHandler's action.
     *
     * Saves all items within the repository.
     */
    @Override
    public void action(){
        try
        {
            FileOutputStream file = new FileOutputStream(_filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(_repo.get());

            out.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
