package gabriel.infra.repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.repository.UserRepository;

public class UserFileRepository implements UserRepository {

    @Override
    public Collection<User> findAll() {
        try {
            FileInputStream fis = new FileInputStream("users.txt");

            InputStreamReader reader = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();
            System.out.println(line);

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
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
