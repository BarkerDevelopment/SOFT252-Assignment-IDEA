package test.models.users.info;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.models.users.Doctor;
import soft252.models.users.Patient;
import soft252.models.users.User;
import soft252.models.users.UserRepository;
import soft252.models.users.info.Gender;
import soft252.models.users.info.ID;
import soft252.models.users.info.Role;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IDTest {
    private static final long SEED = 42;

    private static UserRepository _instance;

    @BeforeAll
    public static void setUpClass() {
        _instance = UserRepository.getInstance();

        for(int i = 0; i < 5; i++){
                new Patient(
                        "Test",
                        "Test",
                        "password".hashCode(),
                        LocalDate.of(1999, 1, 1),
                        Gender.MALE,
                        i * SEED).include();
        }

        /*
         * This will created a doctor with the same ID number as the first
         * patient but the overall ID will be unique due to the User role prefix.
         */
        new Doctor(
                "Test",
                "Test",
                "password".hashCode(),
                SEED).include();
    }

    /**
     * Test of generateId method, of class ID.
     */
    @Test
    @DisplayName("generateId")
    public void testGenerateId(){
        for(int i = 0; i < 5; i++){
            ID result = new ID(Role.PATIENT, i * SEED);
            System.out.println("Generated :" + result);

            // Checks new ID against all existing IDs.
            for(Role r: Role.values()){
                for (User user : _instance.get(r)) {
                    ID expResult = user.getId();
                    System.out.println(expResult);

                    assertNotEquals(expResult.toString(), result.toString());
                }
            }

            System.out.println();
        }
    }
}