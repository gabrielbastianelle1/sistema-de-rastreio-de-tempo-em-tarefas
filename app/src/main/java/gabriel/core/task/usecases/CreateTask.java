package gabriel.core.task.usecases;

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
        Integer lastId = input.repository().findLastId();
        Task task = new Task.Builder(lastId + 1, input.description(), input.startDate(),
                input.user()).build();
        input.repository().save(task);

        return new Output(task.getId());
    }

}
