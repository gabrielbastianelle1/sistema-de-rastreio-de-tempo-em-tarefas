package gabriel.core.user.repository;

import java.util.Collection;

import gabriel.core.user.domain.User;

public interface UserRepository {
    public Collection<User> findAll();

    public void save(User user);

    public User findByUsername(String username);
}
