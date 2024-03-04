package gabriel.core.user.exceptions;

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException() {
        super("username already taken");
    }

    public UsernameTakenException(String msg) {
        super(msg);
    }
}
