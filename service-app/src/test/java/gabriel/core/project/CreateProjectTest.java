package gabriel.core.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.project.domain.Project;
import gabriel.core.project.dto.CreateProjectDto;
import gabriel.core.project.repository.ProjectRepository;
import gabriel.core.project.usecases.CreateProject;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.repository.UserProjectRepository;

public class CreateProjectTest {

    private final User user = mock(User.class);
    private final ProjectRepository projectRepository = mock(ProjectRepository.class);
    private final UserProjectRepository userProjectRepository = mock(UserProjectRepository.class);

    @ParameterizedTest
    @ValueSource(classes = { CreateProject.class })
    public void testSuccessCreateProjet(
            Class<? extends UseCaseAbstraction<CreateProjectDto.Input, CreateProjectDto.Output>> clazz)
            throws Exception {
        UseCaseAbstraction<CreateProjectDto.Input, CreateProjectDto.Output> createProject = clazz
                .getDeclaredConstructor(CreateProjectDto.Input.class)
                .newInstance(
                        new CreateProjectDto.Input(projectRepository, userProjectRepository, user, "test project", 10));

        CreateProjectDto.Output result = createProject.execute();

        assertEquals(result.project().getUser(), user);
        assertEquals(result.userProject().getUser(), user);
        assertEquals(result.userProject().getProject(), result.project());

        verify(projectRepository).save(result.project());
        verify(userProjectRepository).save(result.userProject());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "gabriel&", "*", "#" })
    public void testProjectNames(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Project.Builder(null, user, name));
    }

}
