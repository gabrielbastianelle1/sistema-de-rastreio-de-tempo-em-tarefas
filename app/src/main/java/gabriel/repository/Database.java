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
    public String save(User user) {
        this.users.put(user.getUsername(), user);
        return user.getUsername().getUsername();
    }

    @Override
    public User findById(String id) {
        return this.users.get(new Username(id));
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean update(String id, User updated) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
