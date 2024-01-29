package gabriel.infra.repository;

import java.util.Collection;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.repository.UserRepository;

public class UserSqlRepository implements UserRepository {

    @Override
    public Collection<User> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Username save(User t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public User findById(Username id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean delete(Username id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean update(Username id, User updated) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
