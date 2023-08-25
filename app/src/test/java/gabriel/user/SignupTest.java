package gabriel.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.user.domain.User;
import gabriel.core.user.exceptions.UsernameTakenException;
import gabriel.core.user.interfaces.AuthenticationAbstraction;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signup;

public class SignupTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final Collection<User> users = new ArrayList<>();
    private final String username = "test";
    private final String password = "123";

    private final User newUser = new User(username, password);

    @BeforeEach
    public void initAll() {
        when(userRepository.findAll()).thenReturn(users);
    }

    @ParameterizedTest
    @ValueSource(classes = { Signup.class })
    public void testSignup_SuccessSignup(Class<? extends AuthenticationAbstraction> signupClass) throws Exception {
        AuthenticationAbstraction signup = signupClass.getDeclaredConstructor(UserRepository.class)
                .newInstance(userRepository);

        User user = signup.execute(username, password);
        verify(userRepository).save(newUser);
        users.add(newUser);
        assertTrue(userRepository.findAll().contains(newUser));
        assertEquals(new User(username, password), user);
    }

    @ParameterizedTest
    @ValueSource(classes = { Signup.class })
    public void testSignup_UsernameAlreadyExists(Class<? extends AuthenticationAbstraction> signupClass)
            throws Exception {
        AuthenticationAbstraction signup = signupClass.getDeclaredConstructor(UserRepository.class)
                .newInstance(userRepository);

        when(userRepository.findByUsername(username))
                .thenReturn(new User(username, password));
        assertThrows(UsernameTakenException.class, () -> signup.execute(username,
                password));
    }

    static Stream<Arguments> signupData() {
        List<Class<? extends AuthenticationAbstraction>> signupClasses = Arrays.asList(
                Signup.class);

        return signupClasses.stream()
                .flatMap(clazz -> Stream.of(
                        arguments(clazz, "hello asdas a"),
                        arguments(clazz, "test*aaa"),
                        arguments(clazz, "a"),
                        arguments(clazz, "kkkk&9"),
                        arguments(clazz, ""),
                        arguments(clazz, " ")));
    }

    @ParameterizedTest
    @MethodSource("signupData")
    public void testSignup_InvalidUsername(Class<? extends AuthenticationAbstraction> signupClass, String username)
            throws Exception {
        AuthenticationAbstraction signup = signupClass.getDeclaredConstructor(UserRepository.class)
                .newInstance(userRepository);

        assertThrows(IllegalArgumentException.class, () -> signup.execute(username, password));
    }
}
