package gabriel.core.task.usecases;

import gabriel.core.UseCase;
import gabriel.core.project.domain.Project;
import gabriel.core.task.domain.Task;
import gabriel.core.task.repository.TaskRepository;

public class AssociateTask implements UseCase<Boolean> {

    private final TaskRepository repository;
    private final Task task;
    private final Project project;

    public AssociateTask(TaskRepository repository, Task task, Project project) {
        this.repository = repository;
        this.task = task;
        this.project = project;
    }

    @Override
    public Boolean execute() {
        if (task.getProject() != null) {
            throw new IllegalArgumentException("task already taken");
        }

        if (task.getEndDate() != null) {
            throw new IllegalArgumentException("task has ended");
        }

        task.setProject(project);
        repository.update(task.getId(), task);
        return true;
    }

}
