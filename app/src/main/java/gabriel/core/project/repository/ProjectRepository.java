package gabriel.core.project.repository;

import java.util.Collection;
import java.util.UUID;

import gabriel.core.project.domain.Project;
import gabriel.core.task.domain.Task;
import gabriel.core.user.domain.User;
import gabriel.repository.interfaces.RepositoryInterface;

public interface ProjectRepository extends RepositoryInterface<Project, UUID> {
    public abstract Collection<Project> findAllProjectsByUser(User user);

    public abstract Collection<Task> findAllTaskByProject(Project project);
}
