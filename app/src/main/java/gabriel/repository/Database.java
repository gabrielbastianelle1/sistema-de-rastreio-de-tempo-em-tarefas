package gabriel.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.repository.UserRepository;

public class Database implements UserRepository {

    private Map<Username, User> users = new HashMap<Username, User>();

    @Override
    public Collection<User> findAll() {
        return this.users.values();
    }

    @Override
    public void save(User user) {
        this.users.put(user.getUsername(), user);
    }

    @Override
    public User findByUsername(String username) {
        return this.users.get(new Username(username));
    }

}
