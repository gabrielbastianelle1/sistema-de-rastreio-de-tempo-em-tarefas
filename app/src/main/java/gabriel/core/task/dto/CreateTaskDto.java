package gabriel.core.task.dto;

import java.time.LocalDateTime;

import gabriel.core.UseCaseDto;
import gabriel.core.task.domain.Task;
import gabriel.core.task.repository.TaskRepository;
import gabriel.core.user.domain.User;

public class CreateTaskDto {

    public static final record Input(TaskRepository repository, User user, String description,
            LocalDateTime startDate) implements UseCaseDto.Input {

    }

    public static final record Output(Task task) implements UseCaseDto.Output {
    }

}
