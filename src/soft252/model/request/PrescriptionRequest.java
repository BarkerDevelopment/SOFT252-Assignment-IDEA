package soft252.model.request;

import soft252.exceptions.StockLevelException;
import soft252.model.I_Observer;
import soft252.model.drugs.Drug;
import soft252.model.drugs.DrugPrescription;
import soft252.model.drugs.DrugRepository;
import soft252.model.user.Patient;
import soft252.model.user.messaging.Message;

import java.time.LocalDate;

/**
 * A class that encapsulates a request for a prescription to be given to a patient.
 */
public class PrescriptionRequest extends Request
        implements I_Observer<Integer> {

    private final Patient _patient;
    private final DrugPrescription _prescription;
    private final Drug _drug;
    private int _drugStock;

    /**
     * Default constructor.
     *
     * @param patient the patient the prescription needs to be delivered to.
     * @param prescription the prescription the patient needs.
     */
    public PrescriptionRequest(Patient patient, DrugPrescription prescription) {
        _patient = patient;
        _prescription = prescription;
        _drug = (Drug) _prescription.getTreatment();

        DrugRepository.getInstance().get(_drug).subscribe(this);
    }

    /**
     * The action following request approval.
     */
    @Override
    protected void approveAction() throws StockLevelException {
        int prescriptionQty = _prescription.getQty();

        if(_drugStock > prescriptionQty){
            _prescription.setStartDate( LocalDate.now() );
            DrugRepository.getInstance().updateStock(_drug, -(prescriptionQty) );

            _patient.addPrescription(_prescription);
            _patient.addMessage(new Message(String.format("Your prescription for %s can now be picked up.", _drug.getName() ) ) );

        }else{
            throw new StockLevelException("Insufficient stock to give prescription to patient.");
        }
    }

    /**
     * The action following request denial.
     */
    @Override
    protected void denyAction() {
        _patient.addMessage(new Message(String.format("Your prescription for %s has been denied.", _drug.getName() ) ) );
    }

    /**
     * Overridden destroy function that implements the super's functionality whilst unsubscribing the object from it's
     * observable.
     */
    @Override
    protected void destroy(){
        DrugRepository.getInstance().get(_drug).unsubscribe(this);
        super.destroy();
    }

    /**
     * @param item the updated stock of the drug.
     */
    @Override
    public void update(Integer item) {
        _drugStock = item;
    }
}
