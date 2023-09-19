package gabriel.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.domain.Task;
import gabriel.core.task.dto.DeleteTaskDto;
import gabriel.core.task.usecases.DeleteTask;

public class DeleteTaskTest extends TaskTest {

    private final UUID id = UUID.randomUUID();
    private final Task task = new Task.Builder(id, null, null, null).build();
    private final UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output> deleteTask = new DeleteTask(
            new DeleteTaskDto.Input(repository, task));

    @Test
    public void testSuccessDelete() {
        when(repository.delete(id)).thenReturn(true);

        DeleteTaskDto.Output result = deleteTask.execute();
        assertTrue(result.result());
        verify(repository).delete(id);

    }

    @Test
    public void testInvalidDelete() {
        when(repository.delete(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> deleteTask.execute());
        verify(repository).delete(id);

    }

}
