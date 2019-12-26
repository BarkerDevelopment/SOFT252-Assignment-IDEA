package soft252.services.serialization;

import soft252.models.I_Repository;
import soft252.models.appointments.AppointmentRepository;
import soft252.models.drugs.DrugRepository;
import soft252.models.request.RequestRepository;
import soft252.models.users.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that loads all Repositories with their saved data.
 */
public class RepositoryLoaderHandler implements I_SerializationHandler{
    private ArrayList< I_Repository< ? > > _repos;

    /**
     * Default constructor.
     */
    public RepositoryLoaderHandler() {
        _repos = new ArrayList<>(
                Arrays.asList(AppointmentRepository.getInstance(), DrugRepository.getInstance(),
                        RequestRepository.getInstance(), UserRepository.getInstance())
        );
    }

    /**
     * Performs the SerializationHandler's action.
     *
     * Loads all Repositories with they saved data.
     */
    @Override
    public void action() {
        for (I_Repository< ? > repo : _repos) repo.load();
    }
}
