package gabriel.core.task.repository;

import java.util.Collection;

import gabriel.core.task.domain.Task;
import gabriel.core.user.domain.User;
import gabriel.repository.interfaces.IdInterface;
import gabriel.repository.interfaces.RepositoryInterface;

public interface TaskRepository extends RepositoryInterface<Task, Integer>, IdInterface<Integer> {
    public abstract Collection<Task> findAllByUser(User user);
}
