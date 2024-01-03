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
    public Username save(User user) {
        this.users.put(user.getUsername(), user);
        return user.getUsername();
    }

    @Override
    public User findById(Username id) {
        return this.users.get(id);
    }

    @Override
    public boolean delete(Username id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean update(Username id, User updated) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
