package soft252.model.request;

import soft252.model.user.Patient;
import soft252.model.user.UserRepository;
import soft252.model.user.info.Address;
import soft252.model.user.info.Gender;

import java.time.LocalDate;

/**
 * A class that encapsulates a request to create a new Patient object.
 */
public class AccountCreationRequest extends Request {

    private String _name;
    private String _surname;
    private Address _address;
    private String _password;
    private LocalDate _dob;
    private Gender _gender;

    /**
     * Creates a patient request that encapsulates a patients details in order to create a Patient object once approved.
     *
     * @param name the Patient's name.
     * @param surname the Patient's surname.
     * @param address the Patient's address
     * @param password the Patient's password.
     * @param dob the Patient's date of birth.
     * @param gender the Patient's gender.
     */
    public AccountCreationRequest(String name, String surname, Address address, String password, LocalDate dob, Gender gender) {
        _name = name;
        _surname = surname;
        _address = address;
        _password = password;
        _dob = dob;
        _gender = gender;
    }

    /**
     * @return the _name variable.
     */
    public String getName() {
        return _name;
    }

    /**
     * @return the _surname variable.
     */
    public String getSurname() {
        return _surname;
    }

    /**
     * @return the _address variable.
     */
    public Address getAddress() {
        return _address;
    }

    /**
     * @return the _password variable.
     */
    public String getPassword() {
        return _password;
    }

    /**
     * @return the _dob variable.
     */
    public LocalDate getDob() {
        return _dob;
    }

    /**
     * @return the _gender variable.
     */
    public Gender getGender() {
        return _gender;
    }

    /**
     * The action following request approval.
     */
    @Override
    protected void approveAction() {
        UserRepository.getInstance().add( new Patient( this ) );
    }

    /**
     * The action following request denial.
     */
    @Override
    protected void denyAction() {
        // For this particular instance, a denied request does nothing except be removed from the list.
    }
}
