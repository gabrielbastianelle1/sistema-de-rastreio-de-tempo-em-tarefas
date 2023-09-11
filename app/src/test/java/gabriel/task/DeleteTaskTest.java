package gabriel.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.domain.Task;
import gabriel.core.task.dto.DeleteTaskDto;
import gabriel.core.task.usecases.DeleteTask;

public class DeleteTaskTest extends TaskTest {

    @ParameterizedTest
    @ValueSource(classes = { DeleteTask.class })
    public void testSuccessDelete(Class<? extends UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output>> clazz)
            throws Exception {
        Task task = new Task.Builder(10, null, null, null).build();

        UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output> deleteTask = clazz
                .getDeclaredConstructor(DeleteTaskDto.Input.class)
                .newInstance(new DeleteTaskDto.Input(repository, task));

        when(repository.delete(10)).thenReturn(true);

        DeleteTaskDto.Output result = deleteTask.execute();
        assertTrue(result.result());
        verify(repository).delete(10);

    }

    @ParameterizedTest
    @ValueSource(classes = { DeleteTask.class })
    public void testInvalidDelete(Class<? extends UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output>> clazz)
            throws Exception {
        Task task = new Task.Builder(10, null, null, null).build();

        UseCaseAbstraction<DeleteTaskDto.Input, DeleteTaskDto.Output> deleteTask = clazz
                .getDeclaredConstructor(DeleteTaskDto.Input.class)
                .newInstance(new DeleteTaskDto.Input(repository, task));

        when(repository.delete(10)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> deleteTask.execute());
        verify(repository).delete(10);

    }

}
