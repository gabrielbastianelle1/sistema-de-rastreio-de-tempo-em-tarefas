package gabriel.core.invitation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.invitation.domain.Invitation;
import gabriel.core.invitation.domain.InvitationState;
import gabriel.core.invitation.dto.InviteUserDto;
import gabriel.core.invitation.exception.DuplicatedUserException;
import gabriel.core.invitation.exception.InvitationAlreadyExistException;
import gabriel.core.invitation.repository.InvitationRepository;
import gabriel.core.invitation.usacases.InviteUser;
import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.repository.UserProjectRepository;

public class InviteUserTest {

    private final InvitationRepository invitationRepository = mock(InvitationRepository.class);
    private final UserProjectRepository userProjectRepository = mock(UserProjectRepository.class);
    private final Project project = mock(Project.class);
    private final User sender = mock(User.class);
    private final User receiver = mock(User.class);
    private UseCaseAbstraction<InviteUserDto.Input, InviteUserDto.Output> inviteUser = new InviteUser(
            new InviteUserDto.Input(invitationRepository, userProjectRepository, sender, receiver, project));

    /**
     * Test sucess invitation
     */
    @Test
    public void testSuccess() {
        InviteUserDto.Output result = inviteUser.execute();

        assertNotNull(result.invitation());
        assertEquals(project, result.invitation().getProject());
        assertEquals(sender, result.invitation().getSender());
        assertEquals(receiver, result.invitation().getReceiver());
        assertEquals(UUID.class, result.invitation().getId().getClass());
        verify(invitationRepository).save(result.invitation());
    }

    /**
     * It must have only invitation per project and user at a time
     */

    @Test
    public void testErrorInvitationAlreadyExist() {
        when(invitationRepository.findAllActiveInvitationByUser(sender)).thenReturn(new ArrayList<Invitation>() {
            {
                add(new Invitation.Builder(UUID.randomUUID(), sender, receiver, project)
                        .withInvitationState(InvitationState.SENT).build());
            }
        });

        assertThrows(InvitationAlreadyExistException.class, () -> inviteUser.execute());
        verify(invitationRepository).findAllActiveInvitationByUser(sender);
    }

    /**
     * Cannot have two times same user to project
     */

    @Test
    public void testErrorUserAlreadyInTheProject() {
        when(userProjectRepository.findAllUsersByProject(project)).thenReturn(new ArrayList<User>() {
            {
                add(receiver);
            }
        });

        assertThrows(DuplicatedUserException.class, () -> inviteUser.execute());
        verify(userProjectRepository).findAllUsersByProject(project);
    }

}