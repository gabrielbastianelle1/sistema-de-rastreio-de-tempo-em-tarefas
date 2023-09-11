package gabriel.core.task.domain;

import java.time.LocalDateTime;

import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;

public final class Task {

    private final Integer id;
    private final String description;
    private final LocalDateTime startDate;
    private LocalDateTime endDate;
    private final User user;
    private Project project;

    private Task(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.user = builder.user;
        this.project = builder.project;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Task other = (Task) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public static final class Builder {

        private final Integer id;
        private final String description;
        private final LocalDateTime startDate;
        private LocalDateTime endDate;
        private final User user;
        private Project project;

        public Builder(Integer id, String description, LocalDateTime startDate, User user) {
            this.id = id;
            this.description = description;
            if (startDate != null) {
                this.startDate = startDate;
            } else {
                this.startDate = LocalDateTime.now();
            }
            this.user = user;
        }

        public Builder withEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withProject(Project project) {
            this.project = project;
            return this;
        }

        public Task build() {
            return new Task(this);
        }

    }

}
