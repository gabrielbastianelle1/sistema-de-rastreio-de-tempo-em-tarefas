package gabriel.core.user.interfaces;

import gabriel.core.user.domain.User;

public interface SigninInterface {
    public User signin(String username, String password);
}
