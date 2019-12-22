package soft252.model.request;

import soft252.model.user.Patient;
import soft252.model.user.UserRepository;
import soft252.model.user.messaging.Message;

/**
 * A class that encapsulates a request to delete an existing Patient object from the repository.
 */
public class AccountTerminationRequest extends Request{

    private final Patient _requester;

    /**
     * Creates a account termination request object that when approved will delete the Patient's account.
     *
     * @param requester the Patient that requested the account termination.
     */
    public AccountTerminationRequest(Patient requester) {
        _requester = requester;
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
