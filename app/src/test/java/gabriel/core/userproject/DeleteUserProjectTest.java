package gabriel.core.userproject;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.repository.UserProjectRepository;
import gabriel.core.userProject.usecases.DeleteUserProject;

public class DeleteUserProjectTest {

    private DeleteUserProject deleteUserProject;

    private final UserProjectRepository userProjectRepository = mock(UserProjectRepository.class);
    private final User userToBeDeleted = mock(User.class);
    private final Project project = mock(Project.class);

    @Test
    public void testSuccess() {
        deleteUserProject = new DeleteUserProject(
                new DeleteUserProject.Input(userProjectRepository, userToBeDeleted, project));

        when(userProjectRepository.deleteByUserAndProject(project, userToBeDeleted)).thenReturn(true);

        DeleteUserProject.Output result = deleteUserProject.execute();

        assertTrue(result.result());
        verify(userProjectRepository).deleteByUserAndProject(project, userToBeDeleted);
    }

}
