package gabriel.core.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.dto.SignupDto;
import gabriel.core.user.exceptions.UsernameTakenException;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signup;

public class SignupTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final Collection<User> users = new ArrayList<>();
    private final String username = "test";
    private final String name = "gabriel";
    private final String password = "123";

    private final UseCaseAbstraction<SignupDto.Input, SignupDto.Output> signup = new Signup(
            new SignupDto.Input(userRepository, username, password, name, 0));

    @BeforeEach
    public void initAll() {
        when(userRepository.findAll()).thenReturn(users);
    }

    @Test
    public void testSignup_SuccessSignup() {
        SignupDto.Output output = signup.execute();

        verify(userRepository).save(output.user());
        users.add(output.user());

        assertTrue(userRepository.findAll().contains(output.user()));
        assertEquals(new User.Builder(new Username(username), password).build(), output.user());
    }

    @Test
    public void testSignup_UsernameAlreadyExists() {
        when(userRepository.findById(new Username(username)))
                .thenReturn(new User.Builder(new Username(username), password).build());
        assertThrows(UsernameTakenException.class, () -> signup.execute());
    }

    // static Stream<Arguments> signupData() {
    // List<Class<? extends AuthenticationAbstraction>> signupClasses =
    // Arrays.asList(
    // Signup.class);

    // return signupClasses.stream()
    // .flatMap(clazz -> Stream.of(
    // arguments(clazz, "hello asdas a"),
    // arguments(clazz, "test*aaa"),
    // arguments(clazz, "a"),
    // arguments(clazz, "kkkk&9"),
    // arguments(clazz, ""),
    // arguments(clazz, " ")));
    // }

    @ParameterizedTest
    @ValueSource(strings = { "hello asd a", "test*aaaa", "a", "kkkkk&9", "", " " })
    public void testSignup_InvalidUsername(String username)
            throws Exception {
        UseCaseAbstraction<SignupDto.Input, SignupDto.Output> signup = new Signup(
                new SignupDto.Input(userRepository, username, password, name, 0));

        assertThrows(IllegalArgumentException.class, () -> signup.execute());
    }
}
