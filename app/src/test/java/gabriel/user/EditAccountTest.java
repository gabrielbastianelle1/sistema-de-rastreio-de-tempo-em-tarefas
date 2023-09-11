package gabriel.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.user.domain.User;
import gabriel.core.user.interfaces.EditAccountAbstraction;
import gabriel.core.user.usecases.EditAccount;

public class EditAccountTest {

    private final User user = new User("gabriel", "123", "gabriel", 40);

    @ParameterizedTest
    @ValueSource(classes = { EditAccount.class })
    public void testEditName(Class<? extends EditAccountAbstraction> clazz) throws Exception {
        EditAccountAbstraction editAccountAbstraction = clazz.getConstructor(User.class).newInstance(user);

        assertEquals(user.getName(), "gabriel");
        editAccountAbstraction.editName("new name");
        assertEquals(user.getName(), "new name");

    }

    @ParameterizedTest
    @ValueSource(classes = { EditAccount.class })
    public void testEditWorkingHour(Class<? extends EditAccountAbstraction> clazz) throws Exception {
        EditAccountAbstraction editAccountAbstraction = clazz.getConstructor(User.class).newInstance(user);

        assertEquals(user.getworkingHours(), 40);
        editAccountAbstraction.editEditWorkingHour(50);
        assertEquals(user.getworkingHours(), 50);

    }

}
