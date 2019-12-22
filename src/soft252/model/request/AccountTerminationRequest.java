package soft252.model.request;

import soft252.model.user.Patient;
import soft252.model.user.UserRepository;

/**
 * A class that encapsulates a request to delete an existing Patient object from the repository.
 */
public class AccountTerminationRequest extends Request{

    private final Patient _requester;

    /**
     * Default constructor.
     * Additionally, this constructor adds the resultant object to its corresponding repository: RequestRepository.
     *
     * @param requester the Patient that requested the account termination.
     */
    public AccountTerminationRequest(Patient requester) {
        _requester = requester;

        RequestRepository.getInstance().add(this);
    }

    /**
     * @return the _requester variable.
     */
    public Patient getRequester() {
        return _requester;
    }


    /**
     * The action following request approval.
     */
    @Override
    protected void approveAction() {
        UserRepository.getInstance().remove(_requester);
    }

    /**
     * The action following request denial.
     */
    @Override
    protected void denyAction() {
        sendMessage(_requester, "Your account termination request has been denied.");
    }
}
