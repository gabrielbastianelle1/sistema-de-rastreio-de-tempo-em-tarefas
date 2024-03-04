package gabriel.core.userProject.domain;

import java.util.UUID;

import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;

public class UserProject {
    private final UUID id;
    private final Project project;
    private final User user;

    private UserProject(Builder builder) {
        this.id = builder.id;
        this.project = builder.project;
        this.user = builder.user;
    }

    public static final class Builder {
        private final UUID id;
        private final Project project;
        private final User user;

        public Builder(UUID id, Project project, User user) {
            this.id = id;
            this.project = project;
            this.user = user;
        }

        public UserProject build() {
            return new UserProject(this);
        }

    }

    public UUID getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

}
