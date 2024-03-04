package gabriel.core.user.interfaces;

import gabriel.core.user.domain.User;

public interface AuthenticationInterface {
    public abstract User execute(String username, String password);
}
