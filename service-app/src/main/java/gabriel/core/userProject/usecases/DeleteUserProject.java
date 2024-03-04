package gabriel.core.userProject.usecases;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.UseCaseDto;
import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.repository.UserProjectRepository;

public class DeleteUserProject extends UseCaseAbstraction<DeleteUserProject.Input, DeleteUserProject.Output> {

    public DeleteUserProject(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        return new Output(input.userProjectRepository.deleteByUserAndProject(input.project, input.userToBeDeleted));
    }

    /**
     * Input
     */
    public static record Input(UserProjectRepository userProjectRepository, User userToBeDeleted, Project project)
            implements UseCaseDto.Input {
    }

    /**
     * Output
     */
    public static record Output(Boolean result) implements UseCaseDto.Output {
    }

}
