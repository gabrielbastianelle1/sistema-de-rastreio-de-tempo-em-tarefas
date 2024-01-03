package gabriel.task;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.domain.Task;
import gabriel.core.task.dto.CompleteTaskDto;
import gabriel.core.task.usecases.CompleteTask;

public class CompleteTaskTest extends TaskTest {

    private final Task task = new Task.Builder(UUID.randomUUID(), null, LocalDateTime.of(2017, 12, 05, 11, 30), null)
            .build();

    @ParameterizedTest
    @ValueSource(classes = { CompleteTask.class })
    public void testSuccessPassingNoDate(
            Class<? extends UseCaseAbstraction<CompleteTaskDto.Input, CompleteTaskDto.Output>> clazz)
            throws Exception {

        UseCaseAbstraction<CompleteTaskDto.Input, CompleteTaskDto.Output> completeTask = clazz
                .getDeclaredConstructor(CompleteTaskDto.Input.class)
                .newInstance(new CompleteTaskDto.Input(repository, task, null));

        CompleteTaskDto.Output result = completeTask.execute();
        assertTrue(result.result());
        assertNotNull(task.getEndDate());
        verify(repository).update(task.getId(), task);
    }

    // @ParameterizedTest
    // @ValueSource(classes = { CompleteTask.class })
    // public void testSuccessPassingDate(Class<? extends UseCase<Boolean>> clazz)
    // throws Exception {
    // UseCase<Boolean> completeTask = clazz
    // .getDeclaredConstructor(TaskRepository.class, Task.class,
    // LocalDateTime.class)
    // .newInstance(repository, task, LocalDateTime.parse("2017-12-10T11:30:30"));

    // boolean result = completeTask.execute();
    // assertTrue(result);
    // verify(repository).update(task.getId(), task);
    // }

    // @ParameterizedTest
    // @ValueSource(classes = { CompleteTask.class })
    // public void testErrorEndDateBeforeStartDate(Class<? extends UseCase<Boolean>>
    // clazz)
    // throws Exception {
    // UseCase<Boolean> completeTask = clazz
    // .getDeclaredConstructor(TaskRepository.class, Task.class,
    // LocalDateTime.class)
    // .newInstance(repository, task, LocalDateTime.parse("2017-12-03T11:30:30"));

    // assertThrows(IllegalArgumentException.class, () -> completeTask.execute());
    // }

}
