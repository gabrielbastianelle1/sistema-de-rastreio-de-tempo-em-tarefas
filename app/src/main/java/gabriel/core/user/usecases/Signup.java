package gabriel.core.user.usecases;

import gabriel.core.user.domain.User;
import gabriel.core.user.exceptions.UsernameTakenException;
import gabriel.core.user.interfaces.AuthenticationAbstraction;
import gabriel.core.user.repository.UserRepository;

public final class Signup extends AuthenticationAbstraction {

    public Signup(UserRepository userRepository) {
        super(userRepository);
    }

    public User execute(String username, String password) {
        if (userRepository.findById(username) != null)
            throw new UsernameTakenException();

        User user = new User(username, password);
        this.userRepository.save(user);

        return user;
    }
}
