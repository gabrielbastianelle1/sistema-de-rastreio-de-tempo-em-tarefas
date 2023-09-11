package gabriel.core.user.usecases;

import gabriel.core.user.domain.User;
import gabriel.core.user.exceptions.UserNotFoundException;
import gabriel.core.user.interfaces.AuthenticationAbstraction;
import gabriel.core.user.repository.UserRepository;

public final class Signin extends AuthenticationAbstraction {

    public Signin(UserRepository userRepository) {
        super(userRepository);
    }

    public User execute(String username, String password) {
        User user = userRepository.findById(username);
        if (user == null)
            throw new UserNotFoundException("username or password wrong");

        if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("username or password wrong");
        }

        return user;
    }
}
