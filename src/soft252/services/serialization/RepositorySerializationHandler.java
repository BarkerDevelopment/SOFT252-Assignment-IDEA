package soft252.services.serialization;

import soft252.models.I_Repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 */
public class RepositorySerializationHandler
        implements I_SerializationHandler {
    private String _filename;
    private I_Repository< ? > _repo;

    /**
     *
     * @param filename
     * @param repo
     */
    public RepositorySerializationHandler(String filename, I_Repository< ? > repo) {
        _filename = filename;
        _repo = repo;
    }

    /**
     *
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
