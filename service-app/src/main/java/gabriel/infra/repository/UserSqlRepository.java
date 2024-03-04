package gabriel.infra.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.repository.UserRepository;

// 82E2FF
// 10.89

public class UserSqlRepository implements UserRepository {

    private String url;
    private String username;
    private String password;

    public UserSqlRepository() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("db.properties");
            properties.load(fileInputStream);

            this.url = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <T> T executeTransaction(final String sql, Function<PreparedStatement, T> operation)
            throws RuntimeException {

        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement stm = connection.prepareStatement(sql);) {
            connection.setAutoCommit(false);

            T result = operation.apply(stm);

            connection.commit();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<User> findAll() {
        Set<User> users = new HashSet<>();

        return executeTransaction("SELECT * FROM users;", stm -> {
            try (ResultSet resultSet = stm.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User.Builder(new Username(resultSet.getString("username")),
                            resultSet.getString("password"))
                            .withName(resultSet.getString("name"))
                            .withWorkingHours(resultSet.getInt("working_hours"))
                            .build();

                    users.add(user);
                }
                return users;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public User save(final User user) {

        return executeTransaction("INSERT INTO users (username, password, name, working_hours) VALUES (?,?,?,?)",
                stm -> {

                    try {
                        stm.setString(1, user.getUsername().getUsername());
                        stm.setString(2, user.getPassword());
                        stm.setString(3, user.getName());
                        stm.setInt(4, user.getWorkingHours());

                        stm.execute();

                        return user;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public User findById(Username id) {

        return executeTransaction("SELECT * FROM users where username = ?", stm -> {
            try {
                stm.setString(1, id.getUsername());
                try (ResultSet result = stm.executeQuery()) {

                    while (result.next()) {
                        return new User.Builder(new Username(result.getString("username")),
                                result.getString("password"))
                                .withName(result.getString("name"))
                                .withWorkingHours(result.getInt("working_hours"))
                                .build();
                    }

                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

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
