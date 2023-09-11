package gabriel.core.task.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.task.domain.Task;
import gabriel.core.task.repository.TaskRepository;

public class DeleteTaskDto {

    public static final record Input(TaskRepository taskRepository, Task task) implements UseCaseDto.Input {
    }

    public static final record Output(Boolean result) implements UseCaseDto.Output {
    }

}
