package soft252.model.request;

import soft252.exceptions.DrugDuplicateException;
import soft252.model.drugs.Drug;
import soft252.model.drugs.DrugRepository;
import soft252.model.drugs.I_Treatment;
import soft252.model.user.Doctor;
import soft252.model.user.messaging.Message;

import java.util.ArrayList;

/**
 * A request for a new drug to be added to the systm.
 */
public class NewDrugRequest extends Request
    implements I_Treatment {

    private final Doctor _doctor;
    private final String _name;
    private String _description;
    private ArrayList<String> _sideEffects;
    private int _startingQty;

    /**
     *
     * @param requester
     * @param name
     */
    public NewDrugRequest(Doctor requester, String name) throws DrugDuplicateException {
        if(DrugRepository.getInstance().contains(name)) throw new DrugDuplicateException();

        _doctor = requester;
        _name = name;
        _sideEffects = new ArrayList<>();
    }

    /**
     *
     * @param requester
     * @param name
     * @param qty
     */
    public NewDrugRequest(Doctor requester, String name, int qty) throws DrugDuplicateException {
        if(DrugRepository.getInstance().contains(name)) throw new DrugDuplicateException();
        _doctor = requester;
        _name = name;
        _sideEffects = new ArrayList<>();
        _startingQty = qty;
    }

    /**
     * @return the _doctor variable. This is the user that requested the new drug.
     */
    public Doctor getDoctor() {
        return _doctor;
    }

    /**
     * @return the _name variable. This is the name of the new drug.
     */
    @Override
    public String getName() {
        return _name;
    }

    /**
     * @return the _description variable.
     */
    @Override
    public String getDescription() {
        return _description;
    }

    /**
     * @return the _sideEffects variable. This represents the known side effects of the drugs.
     */
    @Override
    public ArrayList< String > getSideEffects() {
        return _sideEffects;
    }

    /**
     * @return the _startingQty variable.
     */
    public int getStartingQty() {
        return _startingQty;
    }

    /**
     * @param description the new drug description.
     */
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * @param sideEffects the drugs side effects.
     */
    public void setSideEffects(ArrayList< String > sideEffects) {
        _sideEffects = sideEffects;
    }

    /**
     * @param startingQty the drug's starting stock level.
     */
    public void setStartingQty(int startingQty) {
        _startingQty = startingQty;
    }

    /**
     * The action following request approval.
     */
    @Override
    protected void approveAction() {
        DrugRepository repository = DrugRepository.getInstance();

        if(_startingQty == 0){
            repository.add(new Drug(_name, _description, _sideEffects));

        }else{
            repository.add(new Drug(_name, _description, _sideEffects), _startingQty);
        }

        sendMessage(_doctor, String.format("Your drug request for %s has been approved.", _name));
    }

    /**
     * The action following request denial.
     */
    @Override
    protected void denyAction() {
        sendMessage(_doctor, String.format("Your drug request for %s has been denied.", _name));
    }
}
