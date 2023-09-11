package gabriel.core.project.usecases;

import java.util.UUID;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.project.domain.Project;
import gabriel.core.project.dto.CreateProjectDto;
import gabriel.core.project.dto.CreateProjectDto.Input;
import gabriel.core.project.dto.CreateProjectDto.Output;
import gabriel.core.userProject.domain.UserProject;

public class CreateProject extends UseCaseAbstraction<CreateProjectDto.Input, CreateProjectDto.Output> {

    public CreateProject(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        Project project = new Project.Builder(UUID.randomUUID(), input.user(), input.name())
                .withPricePerHour(input.pricePerHour()).build();

        UserProject userProject = new UserProject.Builder(UUID.randomUUID(), project, input.user()).build();

        input.projectRepository().save(project);
        input.userProjectRepository().save(userProject);

        return new Output(project, userProject);
    }

}
