package soft252.model.appointment;

import soft252.model.I_Repository;
import soft252.model.user.Doctor;
import soft252.model.user.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A repository of all the drugs in the system.
 */
public class AppointmentRepository
        implements I_Repository<I_Appointment> {

    private static AppointmentRepository _instance;
    private final ArrayList<I_Appointment> _appointments;

    /**
     * Singleton constructor.
     */
    private AppointmentRepository(){
        _appointments = new ArrayList<>();
    }

    /**
     * UserRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static AppointmentRepository getInstance() {
        if(_instance == null) _instance = new AppointmentRepository();

        return _instance;
    }

    /**
     * @return the list of all items in the repo.
     */
    @Override
    public ArrayList<I_Appointment> getAll() {
        return _appointments;
    }

    /**
     * @return all appointments of a specific doctor.
     */
    public ArrayList<I_Appointment> get(Doctor doctor) {
        return new ArrayList<>( _appointments.stream().filter(a -> a.getDoctor().equals(doctor)).collect(Collectors.toList()));
    }

    /**
     * @return all appointments of a specific patient.
     */
    public ArrayList<I_Appointment> get(Patient patient) {
        return new ArrayList<>( _appointments.stream().filter(a -> a.getPatient().equals(patient)).collect(Collectors.toList()));
    }

    /**
     * @return all appointments of a specified day.
     */
    public ArrayList<I_Appointment> get(LocalDate date) {
        return new ArrayList<>( _appointments.stream().filter(a -> a.getDateTime().toLocalDate().equals(date)).collect(Collectors.toList()));
    }

    /**
     * Adds an item to the repository.
     *
     * @param item the new item.
     */
    @Override
    public void add(I_Appointment item) {
        _appointments.add(item);
    }

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    @Override
    public void remove(I_Appointment item) {
        _appointments.remove(item);
    }

    /**
     * Checks if the repository contains the item.
     *
     * @param item the target item.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    @Override
    public boolean contains(I_Appointment item) {
        return _appointments.contains(item);
    }

    /**
     * Clears the repo of all items. This is mainly for testing purposes.
     */
    @Override
    public void clear() {
        _appointments.clear();
    }
}
