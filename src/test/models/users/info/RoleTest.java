package test.models.users.info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import soft252.models.users.info.Role;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    /**
     * Test of values method, of class Role.
     */
    @Test
    @DisplayName("values")
    public void testValues() {
        Role[] expResult = new Role[]{Role.ADMIN, Role.DOCTOR, Role.SECRETARY, Role.PATIENT};
        Role[] result = Role.values();

        assertArrayEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class Role.
     */
    @Test
    @DisplayName("valueOf")
    public void testValueOf() {
        String[] input = new String[]{"ADMIN", "DOCTOR", "SECRETARY", "PATIENT", "GP"};
        Role[] expResult = Role.values();
        Role[] result = new Role[4];

        for(int i = 0; i < 4; i++){
            try{
                result[i] = Role.valueOf(input[i]);
            }catch(Exception e){
                System.out.println(e);
            }
        }

        assertArrayEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Role.
     */
    @Test
    @DisplayName("toString")
    public void testToString() {
        Role[] input = Role.values();
        String[] expResult = new String[]{"A", "D", "S", "P"};
        String[] result = new String[4];

        for(int i = 0; i < 4; i++){
            try{
                result[i] = input[i].toString();
            }catch(Exception e){
                System.out.println(e);
            }
        }

        assertArrayEquals(expResult, result);
    }

    /**
     * Test of fromString method, of class Role.
     */
    @Test
    @DisplayName("fromString")
    public void testFromString() {
        char[] input = new char[] {'A', 'D', 'S', 'P', 'G'};
        Role[] expResult = new Role[]{Role.ADMIN, Role.DOCTOR, Role.SECRETARY, Role.PATIENT};
        Role[] result = new Role[4];

        for(int i = 0; i < 5; i++){
            try{
                result[i] = Role.fromString(input[i]);
            }catch(Exception e){
                System.out.println(e);
            }
        }

        assertArrayEquals(expResult, result);
    }
}