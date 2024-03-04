package gabriel.core.user.interfaces;

import gabriel.core.user.repository.UserRepository;

public abstract class AuthenticationAbstraction implements AuthenticationInterface {
    public final UserRepository userRepository;

    public AuthenticationAbstraction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
