package soft252.models.request;

import soft252.models.users.User;
import soft252.models.users.UserRepository;

/**
 * A class that encapsulates a request to delete an existing Patient object from the repository.
 */
public class AccountTerminationRequest extends Request{

    private final User _requester;

    /**
     * Default constructor.
     * Additionally, this constructor adds the resultant object to its corresponding repository: RequestRepository.
     *
     * @param requester the Patient that requested the account termination.
     */
    public AccountTerminationRequest(User requester) {
        super(RequestType.ACCOUNT_TERMINATION);

        _requester = requester;
    }

    /**
     * @return the _requester variable.
     */
    public User getRequester() {
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
