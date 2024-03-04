package gabriel.core.invitation.repository;

import java.util.Collection;
import java.util.UUID;

import gabriel.core.invitation.domain.Invitation;
import gabriel.core.user.domain.User;
import gabriel.infra.repository.interfaces.RepositoryInterface;

public interface InvitationRepository extends RepositoryInterface<Invitation, UUID> {

    /**
     * Should return only Invitations where the state is InvitationState.SENT
     *
     * @param user
     * @return
     */
    public abstract Collection<Invitation> findAllActiveInvitationByUser(User user);
}
