package soft252.models.users;

import soft252.models.appointments.AppointmentRepository;
import soft252.models.appointments.I_Appointment;
import soft252.models.appointments.I_AppointmentParticipant;
import soft252.models.drugs.I_Prescription;
import soft252.models.request.AccountCreationRequest;
import soft252.models.request.AppointmentRequest;
import soft252.models.request.I_Requester;
import soft252.models.users.feedback.I_Feedback;
import soft252.models.users.feedback.I_FeedbackRecipient;
import soft252.models.users.feedback.I_FeedbackSender;
import soft252.models.users.info.Address;
import soft252.models.users.info.Gender;
import soft252.models.users.info.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A User subclass for the system's patients.
 */
public class Patient extends User
    implements I_AppointmentParticipant, I_FeedbackSender, I_Requester< AppointmentRequest >{

    private static Role ROLE = Role.PATIENT;
    private final LocalDate _dob;
    private final Gender _gender;
    private ArrayList< I_Prescription > _prescriptions;

    /**
     * Creates a patient object based of a request.
     *
     * @param request the request to create an account.
     */
    public Patient(AccountCreationRequest request){
        super(ROLE, request.getName(), request.getSurname(), request.getAddress(), request.getPassword());
        _dob = request.getDob();
        _gender = request.getGender();
        _prescriptions = new ArrayList<>();
    }

    /**
     * Creates an Patient object.
     *
     * @param name the Patient's name.
     * @param surname the Patient's surname.
     * @param address the Patient's address
     * @param password the Patient's password.
     * @param dob the Patient's date of birth.
     * @param gender the Patient's gender.
     */
    public Patient(String name, String surname, Address address, int password, LocalDate dob, Gender gender) {
        super(ROLE, name, surname, address, password);
        _dob = dob;
        _gender = gender;
        _prescriptions = new ArrayList<>();
    }

    /**
     * Creates a dummy Patient object for testing purposes.
     * NOTE: This constructor is used to specifically test the random ID functionality.
     *
     * @param name the Patient's name.
     * @param surname the Patient's surname.
     * @param password the Patient's password.
     * @param dob the User's date of birth.
     * @param gender the Patient's gender.
     * @param seed the pseudo-random generator seed. This ensures repeatable random generation.
     */
    public Patient(String name, String surname, int password, LocalDate dob, Gender gender, long seed) {
        super(ROLE, name, surname, password, seed);
        _dob = dob;
        _gender = gender;
        _prescriptions = new ArrayList<>();
    }

    /**
     /**
     * Creates a bare dummy Patient object for testing purposes. The name and surname are used to provide another element of
     * individuality to increase ease of testing rather than just looking at IDs.
     *
     * @param idNumber the Patient's ID number. This will be added to the Patient role string to create the Patient ID.
     * @param name the Patient's name.
     * @param surname the Patient's surname.
     * @param gender the Patient's gender.
     */
    public Patient(String idNumber, String name, String surname, Gender gender) {
        super(ROLE, idNumber, name, surname);
        _dob = LocalDate.now();
        _gender = gender;
        _prescriptions = new ArrayList<>();
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
     * @return the _prescriptions variable.
     */
    public ArrayList< I_Prescription > getPrescriptions() {
        return _prescriptions;
    }

    /**
     * @param prescriptions the new contents to set _prescriptions to.
     */
    public void setPrescriptions(ArrayList< I_Prescription > prescriptions) {
        _prescriptions = prescriptions;
    }

    /**
     * Add a prescription to the patient's list of prescriptions.
     *
     * @param prescriptions the new prescription.
     */
    public void addPrescription(I_Prescription prescriptions){
        _prescriptions.add(prescriptions);
    }

    /**
     * Removes a prescription to the patient's list of prescriptions.
     *
     * @param prescriptions the target prescription.
     */
    public void removePrescription(I_Prescription prescriptions){
        _prescriptions.remove(prescriptions);
    }

    /**
     *
     * @param doctor
     * @param dateTime
     */
    public void createAppointment(Doctor doctor, LocalDateTime dateTime){
        new AppointmentRequest(this, doctor, dateTime);
    }

    /**
     *
     * @param appointment
     */
    public void cancelAppointment(I_Appointment appointment){
        AppointmentRepository.getInstance().remove(appointment);
    }

    /**
     * @return all appointments associated with the User.
     */
    @Override
    public ArrayList< I_Appointment > getAppointments() {
        return AppointmentRepository.getInstance().get(this);
    }

    /**
     * @return all future appointments associated with the User.
     */
    @Override
    public ArrayList< I_Appointment > getFutureAppointments() {
        ArrayList< I_Appointment > pastAppointments = AppointmentRepository.getInstance().get(this);
        // Remove all dates from the past.
        pastAppointments.removeIf(a -> a.getDateTime().compareTo(LocalDateTime.now()) < 0);

        return pastAppointments;
    }

    /**
     * @return all past appointments associated with the User.
     */
    @Override
    public ArrayList< I_Appointment > getPastAppointments() {
        ArrayList< I_Appointment > pastAppointments = AppointmentRepository.getInstance().get(this);
        // Remove all dates from the future.
        pastAppointments.removeIf(a -> a.getDateTime().compareTo(LocalDateTime.now()) >= 0);

        return pastAppointments;
    }

    /**
     * Sends a piece of feedback to a target.
     *
     * @param recipient the target recipient.
     * @param feedback  the new feedback.
     */
    @Override
    public void giveFeedback(I_FeedbackRecipient recipient, I_Feedback feedback) {
        recipient.addFeedback(feedback);
    }
}
