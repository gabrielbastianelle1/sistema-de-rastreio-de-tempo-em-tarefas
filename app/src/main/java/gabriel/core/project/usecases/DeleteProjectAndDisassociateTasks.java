package gabriel.core.project.usecases;

import java.util.Collection;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.project.dto.DeleteTaskDto;
import gabriel.core.project.dto.DeleteTaskDto.Input;
import gabriel.core.project.dto.DeleteTaskDto.Output;
import gabriel.core.task.domain.Task;

public class DeleteProjectAndDisassociateTasks extends
        UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output> {

    public DeleteProjectAndDisassociateTasks(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        Collection<Task> tasks = input.projectRepository().findAllTaskByProject(input.project());
        tasks.forEach(task -> {
            task.setProject(null);
            input.taskRepository().update(task.getId(), task);
        });
        input.projectRepository().delete(input.project().getProjectId());
        return new DeleteTaskDto.Output(true);
    }

}
