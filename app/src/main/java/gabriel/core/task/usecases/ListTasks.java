package gabriel.core.task.usecases;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.domain.Task;
import gabriel.core.task.dto.ListTasksDto;
import gabriel.core.task.dto.ListTasksDto.Input;
import gabriel.core.task.dto.ListTasksDto.Output;

public class ListTasks extends UseCaseAbstraction<ListTasksDto.Input, ListTasksDto.Output> {

    public ListTasks(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        Collection<Task> tasks = input.getRepository().findAllByUser(input.getUser());

        float totalHoursTaken = tasks.stream().map((task) -> {
            return calculateHoursTaken(task);
        }).reduce((d1, d2) -> d1 + d2).get();

        return new Output(tasks, totalHoursTaken);
    }

    private float calculateHoursTaken(Task task) {
        float durationInHours;

        if (task.getEndDate() != null) {
            durationInHours = (float) Duration.between(task.getStartDate(), task.getEndDate()).toMinutes()
                    / 60;
        } else {
            durationInHours = (float) Duration.between(task.getStartDate(), LocalDateTime.now())
                    .toMinutes()
                    / 60;
        }

        return durationInHours;

    }

}
