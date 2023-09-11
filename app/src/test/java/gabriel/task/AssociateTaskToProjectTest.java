package gabriel.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCase;
import gabriel.core.project.domain.Project;
import gabriel.core.task.domain.Task;
import gabriel.core.task.repository.TaskRepository;
import gabriel.core.task.usecases.AssociateTask;
import gabriel.core.user.domain.User;

public class AssociateTaskToProjectTest extends TaskTest {
    private Task task = mock(Task.class);
    private Project project = mock(Project.class);

    @BeforeEach
    public void init() {
        when(task.getId()).thenReturn(1);
    }

    @ParameterizedTest
    @ValueSource(classes = { AssociateTask.class })
    public void testSuccess(Class<? extends UseCase<Boolean>> clazz) throws Exception {
        UseCase<Boolean> associateTask = clazz.getDeclaredConstructor(TaskRepository.class, Task.class, Project.class)
                .newInstance(repository, task, project);
        when(task.getProject()).thenReturn(null);
        when(task.getEndDate()).thenReturn(null);
        boolean result = associateTask.execute();

        assertTrue(result);
        verify(task).setProject(project);
        verify(repository).update(1, task);
    }

    @ParameterizedTest
    @ValueSource(classes = { AssociateTask.class })
    public void testTaskAlreadyTaken(Class<? extends UseCase<Boolean>> clazz)
            throws Exception {
        UseCase<Boolean> associateTask = clazz.getDeclaredConstructor(TaskRepository.class, Task.class, Project.class)
                .newInstance(repository, task, project);
        when(task.getProject()).thenReturn(new Project.Builder(null, mock(User.class), "test project").build());

        assertThrows(IllegalArgumentException.class, () -> associateTask.execute());
    }

    @ParameterizedTest
    @ValueSource(classes = { AssociateTask.class })
    public void testTaskAlreadyEnded(Class<? extends UseCase<Boolean>> clazz)
            throws Exception {
        UseCase<Boolean> associateTask = clazz.getDeclaredConstructor(TaskRepository.class, Task.class, Project.class)
                .newInstance(repository, task, project);
        when(task.getEndDate()).thenReturn(LocalDateTime.now());

        assertThrows(IllegalArgumentException.class, () -> associateTask.execute());
    }

}
