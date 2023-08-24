package gabriel.core.user.usecases;

import gabriel.core.user.domain.User;
import gabriel.core.user.exceptions.UsernameTakenException;
import gabriel.core.user.interfaces.SignupInterface;
import gabriel.core.user.repository.UserRepository;

public class Signup implements SignupInterface {
    private final UserRepository userRepository;

    public Signup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(String username, String password, String name) {
        if (userRepository.findByUsername(username) != null)
            throw new UsernameTakenException();
        User user = new User(username, password, name);
        this.userRepository.save(user);
    }
}
