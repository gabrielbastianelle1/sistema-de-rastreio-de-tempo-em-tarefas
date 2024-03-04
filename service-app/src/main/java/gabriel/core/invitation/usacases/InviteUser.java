package gabriel.core.invitation.usacases;

import java.util.Collection;
import java.util.UUID;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.invitation.domain.Invitation;
import gabriel.core.invitation.dto.InviteUserDto;
import gabriel.core.invitation.dto.InviteUserDto.Input;
import gabriel.core.invitation.dto.InviteUserDto.Output;
import gabriel.core.invitation.exception.DuplicatedUserException;
import gabriel.core.invitation.exception.InvitationAlreadyExistException;
import gabriel.core.user.domain.User;

public final class InviteUser extends UseCaseAbstraction<InviteUserDto.Input, InviteUserDto.Output> {

    public InviteUser(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        Invitation invitation = new Invitation.Builder(UUID.randomUUID(), input.sender(), input.receiver(),
                input.project())
                .build();

        /**
         * First check if user is already part of project
         */
        Collection<User> usersPartOfProject = input.userProjectRepository().findAllUsersByProject(input.project());

        if (usersPartOfProject.contains(input.receiver())) {
            throw new DuplicatedUserException();
        }

        /**
         * Check is already exist active invitation to the receiver
         */
        Collection<Invitation> userActiveInvitations = input.invitationRepository()
                .findAllActiveInvitationByUser(input.sender());

        if (userActiveInvitations.contains(invitation)) {
            throw new InvitationAlreadyExistException();
        }

        /**
         * Create invitation
         */
        input.invitationRepository().save(invitation);
        return new Output(invitation);
    }

}
