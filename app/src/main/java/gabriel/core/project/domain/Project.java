package gabriel.core.project.domain;

import java.util.UUID;

import gabriel.core.user.domain.User;

public class Project {

    private String regex = "^(?!.*[^\\w\\s]).{5,}$";
    private final UUID projectId;
    private final User user;
    private final String name;
    private float pricePerHour;

    private Project(Builder builder) {
        this.projectId = builder.projectId;
        if (builder.name.matches(regex) == false)
            throw new IllegalArgumentException(
                    "invalid name, it sould have no special caracters and length minimum 5");

        if (pricePerHour < 0) {
            throw new IllegalArgumentException(
                    "price cannot be negative");
        }

        if (builder.user == null) {
            throw new IllegalArgumentException(
                    "user must have a value");
        }

        this.user = builder.user;
        this.name = builder.name;
        this.pricePerHour = builder.pricePerHour;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public String getRegex() {
        return regex;
    }

    public UUID getProjectId() {
        return projectId;
    }

    /**
     * equals and hashCode to field projectId.
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
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
        Project other = (Project) obj;
        if (projectId == null) {
            if (other.projectId != null)
                return false;
        } else if (!projectId.equals(other.projectId))
            return false;
        return true;
    }

    public static final class Builder {
        private String regex = "^(?!.*[^\\w\\s]).{5,}$";
        private final UUID projectId;
        private final User user;
        private final String name;
        private float pricePerHour;

        public Builder(UUID projectId, User user, String name) {
            this.projectId = projectId;
            if (name.matches(regex) == false)
                throw new IllegalArgumentException(
                        "invalid name, it sould have no special caracters and length minimum 5");

            if (user == null) {
                throw new IllegalArgumentException(
                        "user must have a value");
            }

            this.user = user;
            this.name = name;
        }

        public Builder withPricePerHour(float pricePerHour) {
            if (pricePerHour < 0) {
                throw new IllegalArgumentException(
                        "price cannot be negative");
            }

            this.pricePerHour = pricePerHour;
            return this;
        }

        public Project build() {
            return new Project(this);
        }

    }

}
