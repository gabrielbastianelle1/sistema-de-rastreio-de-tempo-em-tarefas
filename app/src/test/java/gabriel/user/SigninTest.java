package gabriel.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.user.domain.User;
import gabriel.core.user.exceptions.UserNotFoundException;
import gabriel.core.user.interfaces.SigninInterface;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signin;

public class SigninTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final String username = "gabriel";
    private final String password = "123";

    @ParameterizedTest
    @ValueSource(classes = { Signin.class })
    public void testSuccess(Class<? extends SigninInterface> signinClass) throws Exception {
        SigninInterface signin = signinClass.getConstructor(UserRepository.class).newInstance(userRepository);

        when(userRepository.findByUsername(username)).thenReturn(new User(username, password, "gabriel"));
        assertEquals(new User(username, password, "gabriel"), signin.signin(username, password));
    }

    @ParameterizedTest
    @ValueSource(classes = { Signin.class })
    public void testUserDoNotExist(Class<? extends SigninInterface> signinClass) throws Exception {
        SigninInterface signin = signinClass.getConstructor(UserRepository.class).newInstance(userRepository);

        when(userRepository.findByUsername(username)).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> signin.signin(username, password));

    }

    @ParameterizedTest
    @ValueSource(classes = { Signin.class })
    public void testWrongPassword(Class<? extends SigninInterface> signinClass) throws Exception {
        SigninInterface signin = signinClass.getConstructor(UserRepository.class).newInstance(userRepository);

        when(userRepository.findByUsername(username)).thenReturn(new User(username, password, "gabriel"));
        assertThrows(UserNotFoundException.class, () -> signin.signin(username, "wrong"));

    }
}
