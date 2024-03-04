package gabriel.core.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import gabriel.core.UseCaseAbstraction;
import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.dto.SigninDto;
import gabriel.core.user.exceptions.UserNotFoundException;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signin;

public class SigninTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final String username = "gabriel";
    private final String password = "123";
    private final UseCaseAbstraction<SigninDto.Input, SigninDto.Output> signin = new Signin(
            new SigninDto.Input(userRepository, username, password));

    @Test
    public void testSuccess() {

        when(userRepository.findById(new Username(username)))
                .thenReturn(new User.Builder(new Username(username), password).build());
        SigninDto.Output output = signin.execute();
        assertEquals(output.user(), new User.Builder(new Username(username), password).build());
    }

    @Test
    public void testUserDoNotExist() {
        when(userRepository.findById(new Username(username))).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> signin.execute());
    }

    @Test
    public void testWrongPassword() {
        when(userRepository.findById(new Username(username)))
                .thenReturn(new User.Builder(new Username(username), "wrong").build());
        assertThrows(UserNotFoundException.class, () -> signin.execute());

    }
}
