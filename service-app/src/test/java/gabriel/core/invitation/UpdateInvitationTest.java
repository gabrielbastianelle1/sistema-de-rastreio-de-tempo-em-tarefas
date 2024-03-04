package gabriel.core.invitation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import gabriel.core.invitation.domain.Invitation;
import gabriel.core.invitation.domain.InvitationState;
import gabriel.core.invitation.repository.InvitationRepository;
import gabriel.core.invitation.usacases.UpdateInvitation;
import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;
import gabriel.core.userProject.repository.UserProjectRepository;

public class UpdateInvitationTest {

    private final Invitation invitation = new Invitation.Builder(UUID.randomUUID(), mock(User.class), mock(User.class),
            mock(Project.class))
            .build();

    private final InvitationRepository invitationRepository = mock(InvitationRepository.class);
    private final UserProjectRepository userProjectRepository = mock(UserProjectRepository.class);

    private UpdateInvitation updateInvitation;

    @Test
    public void testSuccessAccept() {
        updateInvitation = new UpdateInvitation(
                new UpdateInvitation.Input(invitation, invitationRepository, userProjectRepository,
                        InvitationState.ACCEPTED));
        UpdateInvitation.Output result = updateInvitation.execute();

        assertEquals(invitation.getState(), InvitationState.ACCEPTED);
        verify(invitationRepository).update(invitation.getId(), invitation);
        verify(userProjectRepository).save(result.userProject());
    }

    @Test
    public void testSuccessDenied() {
        updateInvitation = new UpdateInvitation(
                new UpdateInvitation.Input(invitation, invitationRepository, userProjectRepository,
                        InvitationState.DENIED));
        UpdateInvitation.Output result = updateInvitation.execute();

        assertEquals(invitation.getState(), InvitationState.DENIED);
        assertNull(result.userProject());
        verify(invitationRepository).update(invitation.getId(), invitation);
        verifyNoInteractions(userProjectRepository);
    }

}
