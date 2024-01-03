package gabriel.core.user.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("user not found");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
