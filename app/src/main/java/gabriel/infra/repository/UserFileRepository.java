package gabriel.infra.repository;

import java.io.File;
import java.util.Collection;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.repository.UserRepository;
import gabriel.infra.parse.CsvParse;

public class UserFileRepository implements UserRepository {

    private final CsvParse<User> csvParse;
    private final File file;

    public UserFileRepository(CsvParse<User> csvParse) {
        this.csvParse = csvParse;
        this.file = new File("users.csv");
    }

    @Override
    public Collection<User> findAll() {
        return csvParse.parseFile(file, ",", User.class);
    }

    @Override
    public User save(User t) {
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
