package gabriel.core.task.usecases;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.dto.DeleteTaskDto;
import gabriel.core.task.dto.DeleteTaskDto.Input;
import gabriel.core.task.dto.DeleteTaskDto.Output;

public class DeleteTask extends UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output> {

    public DeleteTask(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        boolean deleted = input.taskRepository().delete(input.task().getId());
        if (!deleted)
            throw new IllegalArgumentException("error delete");
        return new Output(deleted);
    }

}
