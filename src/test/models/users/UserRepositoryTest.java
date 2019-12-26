package test.models.users;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.models.request.AccountTerminationRequest;
import soft252.models.request.Request;
import soft252.models.request.RequestRepository;
import soft252.models.request.RequestType;
import soft252.models.users.*;
import soft252.models.users.info.Gender;
import soft252.models.users.info.Role;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private static UserRepository _repo;
    private static User[] _users;

    @BeforeEach
    public void setUpClass() {
        _repo = UserRepository.getInstance();

        _users = new User[]{
                new Patient(
                        "1111",
                        "Lucas",
                        "McCarron",
                        Gender.MALE).include(),

                new Patient(
                        "2222",
                        "Lee",
                        "Smith",
                        Gender.MALE).include(),

                new Patient(
                        "3333",
                        "Holly",
                        "Barker",
                        Gender.FEMALE).include(),

                new Admin(
                        "4444",
                        "Max",
                        "Barker").include(),

                new Secretary(
                        "5555",
                        "Harry",
                        "Stradling").include(),

                new Doctor(
                        "6666",
                        "Sophie",
                        "Brockbank").include()
        };
    }

    @AfterEach
    void teardown(){
        _repo.clear();
    }

    /**
     * Test of getUsers method, of class UserRepository.
     *
     * NOTE: The reason for the for loop is because the order of the Users is not important but what is, is whether they
     * are in the array or not.
     */
    @Test
    @DisplayName("getUsers 0args")
    public void testGetAll_0args() {

        ArrayList<User> expResult = new ArrayList<>(Arrays.asList(_users));
        ArrayList<User> result = _repo.get();

        for (User u : result) assertTrue(expResult.contains(u));
    }

    /**
     * Test of getUsers method, of class UserRepository.
     */
    @Test
    @DisplayName("getUsers")
    public void testGetAll_Role() {
        ArrayList<User> expResult = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(_users, 0, 3)));
        ArrayList<User> result =  _repo.get(Role.PATIENT);
        assertEquals(expResult, result);

        expResult = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(_users, 3, 4)));
        result =  _repo.get(Role.ADMIN);
        assertEquals(expResult, result);

        expResult = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(_users, 4, 5)));
        result =  _repo.get(Role.SECRETARY);
        assertEquals(expResult, result);

        expResult = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(_users, 5, 6)));
        result =  _repo.get(Role.DOCTOR);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class UserRepository.
     */
    @Test
    @DisplayName("add")
    public void testAdd() {
        Doctor item = new Doctor("7777", "John", "Cena");
        _repo.add(item);

        assertTrue(_repo.get(Role.DOCTOR).contains(item));

        _repo.remove(item);
    }

    /**
     * Test of remove method, of class UserRepository.
     */
    @Test
    @DisplayName("remove")
    public void testRemove() {
        _repo.remove(_users[0]);

        assertTrue(containsTerminationRequest(_users[0]));
    }

    private boolean containsTerminationRequest(User user){
        ArrayList< Request > requests = RequestRepository.getInstance().get(RequestType.ACCOUNT_TERMINATION);

        for (Request request : requests)
            if(((AccountTerminationRequest) request).getRequester().equals(user)) return true;

        return false;
    }

    /**
     * Test of contains method, of class UserRepository.
     */
    @Test
    @DisplayName("contains")
    public void testContains_User() {
        User item = _users[0];
        assertTrue(_repo.contains(item));

        item = new Doctor("4242", "John", "Cena");
        assertFalse(_repo.contains(item));
    }

    /**
     * Test of contains method, of class UserRepository.
     */
    @Test
    @DisplayName("contains string")
    public void testContains_String() {
        String id = "P1111";
        assertTrue(_repo.contains(id));

        id = "S5555";
        assertTrue(_repo.contains(id));

        id = "A9999";
        assertFalse(_repo.contains(id));
    }

    /**
     * Test of find method, of class UserRepository.
     */
    @Test
    @DisplayName("find")
    public void testFind() {
        String id = "P1111";
        User expResult = _users[0];
        User result = _repo.get(id);
        assertEquals(expResult, result);

        id = "S5555";
        expResult = _users[4];
        result = _repo.get(id);
        assertEquals(expResult, result);

        id = "A9999";
        expResult = null;
        result = _repo.get(id);
        assertEquals(expResult, result);
    }
}