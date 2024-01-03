package gabriel.core.invitation.exception;

public class DuplicatedUserException extends RuntimeException {
    public DuplicatedUserException() {
        super("duplicated user");
    }

    public DuplicatedUserException(String msg) {
        super(msg);
    }
}
