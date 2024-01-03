package gabriel.task;

import static org.mockito.Mockito.mock;

import gabriel.core.task.repository.TaskRepository;

public abstract class TaskTest {
    public final TaskRepository repository = mock(TaskRepository.class);
}
