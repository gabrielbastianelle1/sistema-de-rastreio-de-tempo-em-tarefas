package gabriel.core.user.usecases;

import gabriel.core.user.domain.User;
import gabriel.core.user.exceptions.UserNotFoundException;
import gabriel.core.user.interfaces.SigninInterface;
import gabriel.core.user.repository.UserRepository;

public class Signin implements SigninInterface {
    private final UserRepository userRepository;

    public Signin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signin(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("username or password wrong");

        if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("username or password wrong");
        }

        return user;
    }
}
