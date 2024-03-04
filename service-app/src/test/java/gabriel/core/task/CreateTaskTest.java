package gabriel.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.task.dto.CreateTaskDto;
import gabriel.core.task.usecases.CreateTask;
import gabriel.core.user.domain.User;

public class CreateTaskTest extends TaskTest {

    private final User user = mock(User.class);

    @ParameterizedTest
    @ValueSource(classes = { CreateTask.class })
    public void testSuccess(Class<? extends UseCaseAbstraction<CreateTaskDto.Input, CreateTaskDto.Output>> clazz)
            throws Exception {
        UseCaseAbstraction<CreateTaskDto.Input, CreateTaskDto.Output> createTask = clazz
                .getDeclaredConstructor(CreateTaskDto.Input.class)
                .newInstance(new CreateTaskDto.Input(repository, user, null, null));

        CreateTaskDto.Output result = createTask.execute();

        assertEquals(UUID.class, result.task().getId().getClass());
        verify(repository).save(result.task());
    }

}
