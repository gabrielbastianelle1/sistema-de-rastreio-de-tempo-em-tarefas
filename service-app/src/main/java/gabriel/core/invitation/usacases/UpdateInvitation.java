package gabriel.core.invitation.usacases;

import java.util.UUID;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.UseCaseDto;
import gabriel.core.invitation.domain.Invitation;
import gabriel.core.invitation.domain.InvitationState;
import gabriel.core.invitation.repository.InvitationRepository;
import gabriel.core.userProject.domain.UserProject;
import gabriel.core.userProject.repository.UserProjectRepository;

public class UpdateInvitation extends UseCaseAbstraction<UpdateInvitation.Input, UpdateInvitation.Output> {

    public UpdateInvitation(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        /**
         * Update state of invitation
         */
        input.invitation().setState(input.state());
        input.invitationRepository().update(input.invitation().getId(), input.invitation());

        if (input.state() == InvitationState.DENIED) {
            return new Output(input.invitation(), null);
        }

        /**
         * Create the line user-project, with the project and user (receiver) of
         * invitation
         */
        UserProject userProject = new UserProject.Builder(UUID.randomUUID(), input.invitation().getProject(),
                input.invitation().getReceiver()).build();

        /**
         * Save user-project
         */
        input.userProjectRepository().save(userProject);

        return new Output(input.invitation(), userProject);

    }

    /**
     * Input
     */
    public record Input(Invitation invitation, InvitationRepository invitationRepository,
            UserProjectRepository userProjectRepository,
            InvitationState state)
            implements UseCaseDto.Input {
    }

    /**
     * Output
     */
    public record Output(Invitation invitation, UserProject userProject) implements UseCaseDto.Output {
    }

}
