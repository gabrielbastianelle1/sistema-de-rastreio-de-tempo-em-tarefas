package gabriel.core.task.repository;

import java.util.Collection;
import java.util.UUID;

import gabriel.core.task.domain.Task;
import gabriel.core.user.domain.User;
import gabriel.repository.interfaces.RepositoryInterface;

public interface TaskRepository extends RepositoryInterface<Task, UUID> {
    public abstract Collection<Task> findAllByUser(User user);
}
