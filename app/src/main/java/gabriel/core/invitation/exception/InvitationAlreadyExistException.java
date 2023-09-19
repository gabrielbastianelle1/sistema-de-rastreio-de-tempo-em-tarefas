package gabriel.core.invitation.exception;

public class InvitationAlreadyExistException extends RuntimeException {
    public InvitationAlreadyExistException() {
        super("invitation already exist");
    }

    public InvitationAlreadyExistException(String msg) {
        super(msg);
    }

}
