package gabriel.core.invitation.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.invitation.domain.Invitation;
import gabriel.core.invitation.repository.InvitationRepository;
import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.repository.UserProjectRepository;

public interface InviteUserDto {

    /**
     * Input
     */
    public final static record Input(InvitationRepository invitationRepository,
            UserProjectRepository userProjectRepository, User sender, User receiver,
            Project project) implements UseCaseDto.Input {
    }

    /**
     * Output
     */
    public final static record Output(Invitation invitation) implements UseCaseDto.Output {
    }

}
