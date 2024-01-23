package gabriel.core.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.project.dto.CreateProjectDto;
import gabriel.core.project.usecases.CreateProject;
import gabriel.core.user.domain.User;
import gabriel.core.user.interfaces.EditAccountAbstraction;
import gabriel.core.user.usecases.EditAccount;

public class EditAccountTest {

    private final User user = mock(User.class);

    @ParameterizedTest
    @ValueSource(classes = { EditAccount.class })
    public void testEditName(Class<? extends EditAccountAbstraction> clazz)
            throws Exception {
        EditAccountAbstraction editAccountAbstraction = clazz.getConstructor(User.class).newInstance(user);

        assertEquals(user.getName(), "gabriel");
        editAccountAbstraction.editName("new name");
        assertEquals(user.getName(), "new name");

    }

    // @ParameterizedTest
    // @ValueSource(classes = { CreateProject.class })
    // public void testSuccessCreateProjet(
    // Class<? extends UseCaseAbstraction<CreateProjectDto.Input,
    // CreateProjectDto.Output>> clazz)
    // throws Exception {
    // UseCaseAbstraction<CreateProjectDto.Input, CreateProjectDto.Output>
    // createProject = clazz
    // .getDeclaredConstructor(CreateProjectDto.Input.class)
    // .newInstance(
    // new CreateProjectDto.Input(projectRepository, userProjectRepository, user,
    // "test project", 10));

    // CreateProjectDto.Output result = createProject.execute();

    // assertEquals(result.project().getUser(), user);
    // assertEquals(result.userProject().getUser(), user);
    // assertEquals(result.userProject().getProject(), result.project());

    // verify(projectRepository).save(result.project());
    // verify(userProjectRepository).save(result.userProject());
    // }

    // @ParameterizedTest
    // @ValueSource(classes = { EditAccount.class })
    // public void testEditWorkingHour(Class<? extends EditAccountAbstraction>
    // clazz) throws Exception {
    // EditAccountAbstraction editAccountAbstraction =
    // clazz.getConstructor(User.class).newInstance(user);

    // assertEquals(user.getworkingHours(), 40);
    // editAccountAbstraction.editEditWorkingHour(50);
    // assertEquals(user.getworkingHours(), 50);

    // }

}
