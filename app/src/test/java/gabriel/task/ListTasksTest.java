package gabriel.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.domain.Task;
import gabriel.core.task.domain.TaskState;
import gabriel.core.task.dto.ListTasksDto;
import gabriel.core.task.usecases.ListTasks;
import gabriel.core.user.domain.User;

public class ListTasksTest extends TaskTest {

    private final User user = mock(User.class);
    private final Collection<Task> tasks = new ArrayList<>() {
        {
            add(new Task.Builder(UUID.randomUUID(), null, LocalDateTime.of(2023, 01, 05, 12, 0, 0), user)
                    .withStartDate(LocalDateTime.of(2023, 01, 05, 12, 0, 0))
                    .withEndDate(LocalDateTime.of(2023, 01, 05, 14, 0, 0))
                    .withState(TaskState.DONE).build());
            add(new Task.Builder(UUID.randomUUID(), null, LocalDateTime.of(2023, 01, 06, 12, 0, 0), user)
                    .withStartDate(LocalDateTime.of(2023, 01, 06, 12, 0, 0))
                    .withEndDate(LocalDateTime.of(2023, 01, 06, 14, 30, 0))
                    .withState(TaskState.PROGRESS).build());
        }
    };

    @BeforeEach
    public void init() {
        when(repository.findAllByUser(user)).thenReturn(tasks);
    }

    @ParameterizedTest
    @ValueSource(classes = { ListTasks.class })
    public void testSuccess(Class<? extends UseCaseAbstraction<ListTasksDto.Input, ListTasksDto.Output>> clazz)
            throws Exception {
        UseCaseAbstraction<ListTasksDto.Input, ListTasksDto.Output> listTasks = clazz
                .getDeclaredConstructor(ListTasksDto.Input.class)
                .newInstance(new ListTasksDto.Input(repository, user));
        ListTasksDto.Output result = listTasks.execute();

        assertEquals(tasks, result.getTasks());
        assertEquals(4.5f, result.getHoursTaken());
        verify(repository).findAllByUser(user);

    }

}
