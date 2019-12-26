package soft252.models.users.info;

import soft252.models.I_Printable;
import soft252.models.users.UserRepository;

import java.util.Random;

/**
 * A class that encapsulates the unique ID of a user.
 */
public class ID
        implements I_Printable {
    private static final int ID_LENGTH = 4;

    private final Role _role;
    private final String _idNumber;

    /**
     * Creates a random id object.
     *
     * System.currentTimeMillis is used as the seed to add another element of
     * unpredictability.
     *
     * @param role the initial character of the ID.
     */
    public ID(Role role){
        _role = role;
        _idNumber = generateId(System.currentTimeMillis());
    }

    /**
     * Creates an id object.
     *
     * This constructor is used for testing purposes. A seed ensures repeatable
     * ID generation.
     *
     * @param role the initial character of the ID.
     * @param seed the pseudo-random number generator seed.
     */
    public ID(Role role, long seed){
        _role = role;
        _idNumber = generateId(seed);
    }

    /**
     * Creates a Dummy ID.
     *
     * This constructor is used for testing purposes.
     *
     * @param role the initial character of the ID.
     * @param idNumber a String of ID_LENGTH numbers.
     */
    public ID(Role role, String idNumber){
        _role = role;
        _idNumber = idNumber;
    }

    /**
     * @return the _role variable.
     */
    public Role getRole() {
        return _role;
    }

    /**
     * @return the _idNumber variable.
     */
    public String getIdNumber() {
        return _idNumber;
    }

    /**
     * @return the object as a string variable.
     */
    @Override
    public String toString(){
        return _role + _idNumber;
    }

    /**
     * Generates a random number based on a seed.
     *
     * @param seed the pseudo-random number generator seed.
     * @return a random 4 digit number of length ID_LENGTH.
     */
    private String generateId(long seed){
        UserRepository repo = UserRepository.getInstance();
        Random rand = new Random(seed);

        String idNumber;
        do {

            StringBuilder idNumberBuilder = new StringBuilder();
            for (int i = 0; i < ID_LENGTH; i++) {
                idNumberBuilder.append(rand.nextInt(10));
            }
            idNumber = idNumberBuilder.toString();

            // Loop ensures that generated ID doesn't already exist.
        } while(repo.contains(_role.toString() + idNumber));

        return idNumber;
    }
}
