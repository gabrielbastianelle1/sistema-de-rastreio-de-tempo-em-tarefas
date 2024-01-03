package gabriel.core.task.dto;

import java.time.LocalDateTime;

import gabriel.core.UseCaseDto;
import gabriel.core.task.domain.Task;
import gabriel.core.task.repository.TaskRepository;

public class CompleteTaskDto {

    public static final record Input(TaskRepository taskRepository, Task task, LocalDateTime endDateTime)
            implements UseCaseDto.Input {
    }

    public static final record Output(Boolean result) implements UseCaseDto.Output {
    }
}
