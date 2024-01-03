package gabriel.core.task.dto;

import java.util.Collection;

import gabriel.core.UseCaseDto;
import gabriel.core.task.domain.Task;
import gabriel.core.task.repository.TaskRepository;
import gabriel.core.user.domain.User;

public class ListTasksDto {

    public static final class Input implements UseCaseDto.Input {

        private final TaskRepository repository;
        private final User user;

        public Input(TaskRepository repository, User user) {
            this.repository = repository;
            this.user = user;
        }

        public TaskRepository getRepository() {
            return repository;
        }

        public User getUser() {
            return user;
        }

    }

    public static final class Output implements UseCaseDto.Output {

        private final Collection<Task> tasks;
        private final float hoursTaken;

        public Output(Collection<Task> tasks, float hoursTaken) {
            this.tasks = tasks;
            this.hoursTaken = hoursTaken;
        }

        public Collection<Task> getTasks() {
            return tasks;
        }

        public float getHoursTaken() {
            return hoursTaken;
        }

    }
}
