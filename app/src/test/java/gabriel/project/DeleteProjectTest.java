package gabriel.project;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.project.domain.Project;
import gabriel.core.project.dto.DeleteTaskDto;
import gabriel.core.project.repository.ProjectRepository;
import gabriel.core.project.usecases.DeleteProjectAndDisassociateTasks;
import gabriel.core.project.usecases.DeleteProjectAndTasks;
import gabriel.core.task.domain.Task;
import gabriel.core.task.repository.TaskRepository;

public class DeleteProjectTest {

    private final ProjectRepository projectRepository = mock(ProjectRepository.class);
    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final Project project = mock(Project.class);

    @ParameterizedTest
    @ValueSource(classes = { DeleteProjectAndDisassociateTasks.class, DeleteProjectAndTasks.class })
    public void testDeleteAndDeleteTasksSuccess(
            Class<? extends UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output>> clazz)
            throws Exception {
        UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output> deleteProject = clazz
                .getDeclaredConstructor(DeleteTaskDto.Input.class)
                .newInstance(new DeleteTaskDto.Input(project, projectRepository, taskRepository));

        DeleteTaskDto.Output result = deleteProject.execute();

        assertTrue(result.result());
        verify(projectRepository).findAllTaskByProject(project);
        verify(projectRepository).delete(project.getProjectId());

        if (clazz.equals(DeleteProjectAndTasks.class)) {
            verify(taskRepository, times(projectRepository.findAllTaskByProject(project).size()))
                    .delete(any(UUID.class));
        } else if (clazz.equals(DeleteProjectAndDisassociateTasks.class)) {
            verify(taskRepository, times(projectRepository.findAllTaskByProject(project).size())).update(
                    any(UUID.class),
                    any(Task.class));
        }
    }

}
