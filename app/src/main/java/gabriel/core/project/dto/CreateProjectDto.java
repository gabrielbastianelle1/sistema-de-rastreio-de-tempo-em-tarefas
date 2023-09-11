package gabriel.core.project.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.project.domain.Project;
import gabriel.core.project.repository.ProjectRepository;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.domain.UserProject;
import gabriel.core.userProject.repository.UserProjectRepository;

public class CreateProjectDto {
    /**
     * Input
     */
    public static final record Input(
            ProjectRepository projectRepository, UserProjectRepository userProjectRepository,
            User user,
            String name,
            float pricePerHour) implements UseCaseDto.Input {
    }

    /**
     * Output should have the project created and the line user_project (as the user
     * to project is many to many)
     */
    public static final record Output(Project project, UserProject userProject) implements UseCaseDto.Output {
    }

}
