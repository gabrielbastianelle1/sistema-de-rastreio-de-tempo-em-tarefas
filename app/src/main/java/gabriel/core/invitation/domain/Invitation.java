package gabriel.core.invitation.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;

public class Invitation {

    public final static class Builder {
        private final UUID id;
        private final User sender;
        private final User receiver;
        private final Project project;
        private final LocalDateTime date;
        private InvitationState state;

        /**
         * @param sender
         * @param receiver
         * @param project
         */
        public Builder(UUID id, User sender, User receiver, Project project) {

            if (id == null || sender == null || receiver == null || project == null) {
                throw new IllegalArgumentException("fields cannot be null");
            }

            this.id = UUID.randomUUID();
            this.sender = sender;
            this.receiver = receiver;
            this.project = project;
            this.date = LocalDateTime.now();
            this.state = InvitationState.SENT;

        }

        public Builder withInvitationState(InvitationState state) {
            this.state = state;
            return this;
        }

        public Invitation build() {
            return new Invitation(this);
        }

    }

    private final UUID id;
    private final User sender;
    private final User receiver;
    private final Project project;
    private final LocalDateTime date;
    private InvitationState state;

    /**
     * @param builder
     */
    private Invitation(Builder builder) {
        this.id = builder.id;
        this.sender = builder.sender;
        this.receiver = builder.receiver;
        this.project = builder.project;
        this.date = builder.date;
        this.state = builder.state;
    }

    public UUID getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public Project getProject() {
        return project;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public InvitationState getState() {
        return state;
    }

    public void setState(InvitationState state) {
        this.state = state;
    }

    /**
     * hashCode and equals to: sender, receiver and project
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sender == null) ? 0 : sender.hashCode());
        result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
        result = prime * result + ((project == null) ? 0 : project.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Invitation other = (Invitation) obj;
        if (sender == null) {
            if (other.sender != null)
                return false;
        } else if (!sender.equals(other.sender))
            return false;
        if (receiver == null) {
            if (other.receiver != null)
                return false;
        } else if (!receiver.equals(other.receiver))
            return false;
        if (project == null) {
            if (other.project != null)
                return false;
        } else if (!project.equals(other.project))
            return false;
        return true;
    }

}
