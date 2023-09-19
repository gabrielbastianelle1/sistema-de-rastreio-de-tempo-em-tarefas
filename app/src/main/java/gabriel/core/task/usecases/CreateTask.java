package gabriel.core.task.usecases;

import java.util.UUID;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.domain.Task;
import gabriel.core.task.dto.CreateTaskDto;
import gabriel.core.task.dto.CreateTaskDto.Input;
import gabriel.core.task.dto.CreateTaskDto.Output;

public class CreateTask extends UseCaseAbstraction<CreateTaskDto.Input, CreateTaskDto.Output> {

    public CreateTask(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        Task task = new Task.Builder(UUID.randomUUID(), input.description(), input.startDate(),
                input.user()).build();
        input.repository().save(task);

        return new Output(task);
    }

}
