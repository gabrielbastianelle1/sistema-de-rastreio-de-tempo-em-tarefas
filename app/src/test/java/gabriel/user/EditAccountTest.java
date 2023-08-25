package gabriel.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.user.domain.User;
import gabriel.core.user.interfaces.EditAccountAbstraction;
import gabriel.core.user.usecases.EditAccount;

public class EditAccountTest {

    private final User user = mock(User.class);

    @ParameterizedTest
    @ValueSource(classes = { EditAccount.class })
    public void testEditName(Class<? extends EditAccountAbstraction> clazz) throws Exception {
        EditAccountAbstraction editAccountAbstraction = clazz.getConstructor(User.class).newInstance(user);
        String newName = "new";

        when(user.getName()).thenReturn("lala");

        editAccountAbstraction.editName(newName);
        verify(user).setName(newName);

        assertEquals(user.getName(), newName);

    }

}
