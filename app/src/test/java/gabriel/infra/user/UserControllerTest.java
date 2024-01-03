package gabriel.infra.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.lang.ModuleLayer.Controller;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;

import gabriel.core.user.domain.User;
import gabriel.core.user.usecases.Signup;

public class UserControllerTest {

    // Controller<User, Response> controller;

    // @Test
    // public void signupCall() {
    // controller.signup();
    // Class<?> clazz = Signup.class;

    // // Get the constructors of the class
    // Constructor<?>[] constructors = clazz.getDeclaredConstructors();

    // // Verify if there are constructors present
    // assertTrue(constructors.length > 0);
    // }

}
