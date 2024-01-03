package gabriel.core.project.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.project.domain.Project;
import gabriel.core.project.repository.ProjectRepository;
import gabriel.core.task.repository.TaskRepository;

public class DeleteTaskDto {
    /**
     * Input
     */
    public static final record Input(Project project, ProjectRepository projectRepository,
            TaskRepository taskRepository)
            implements UseCaseDto.Input {
    }

    /**
     * Output
     */
    public static final record Output(Boolean result) implements UseCaseDto.Output {

    }

}
