package gabriel.infra.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.repository.UserRepository;
import gabriel.infra.reflection.ObjectFactory;

public class UserSqlRepository implements UserRepository {

    private ObjectFactory objectFactory;
    private String url;
    private String username;
    private String password;

    public UserSqlRepository(final String url, final String username, final String password,
            ObjectFactory objectFactory) {
        try {
            // Class.forName("org.postgresql.Driver");

            this.objectFactory = objectFactory;
            this.url = url;
            this.username = username;
            this.password = password;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<User> findAll() {
        Set<User> users = new HashSet<>();
        Map<String, String> map = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement stm = connection.prepareStatement("SELECT * from users;");
                ResultSet resultSet = stm.executeQuery()) {
            connection.setAutoCommit(false);

            while (resultSet.next()) {
                map.put("username", resultSet.getString("username"));
                map.put("password", resultSet.getString("password"));
                map.put("name", resultSet.getString("name"));
                map.put("workingHours", resultSet.getString("working_hours"));

                User user = objectFactory.execute(map, User.class);

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User save(final User user) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement stm = connection
                            .prepareStatement(
                                    "INSERT INTO users (username, password, name, working_hours) VALUES (?,?,?,?)")) {

                stm.setString(1, user.getUsername().getUsername());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getName());
                stm.setInt(4, user.getWorkingHours());

                stm.execute();

                connection.commit();

                return user;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
