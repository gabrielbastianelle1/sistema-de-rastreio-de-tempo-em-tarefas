package gabriel.core.task.usecases;

import java.time.LocalDateTime;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.dto.CompleteTaskDto;
import gabriel.core.task.dto.CompleteTaskDto.Input;
import gabriel.core.task.dto.CompleteTaskDto.Output;

public class CompleteTask extends UseCaseAbstraction<CompleteTaskDto.Input, CompleteTaskDto.Output> {

    public CompleteTask(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        if (input.endDateTime() != null) {

            if (input.endDateTime().isBefore(input.task().getStartDate())) {
                throw new IllegalArgumentException("end date shouldnt be before start date");
            }

            input.task().setEndDate(input.endDateTime());
            input.taskRepository().update(input.task().getId(), input.task());
            return new Output(true);
        }

        input.task().setEndDate(LocalDateTime.now());
        input.taskRepository().update(input.task().getId(), input.task());
        return new Output(true);
    }

}
