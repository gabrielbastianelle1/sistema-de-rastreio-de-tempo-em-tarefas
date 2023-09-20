package gabriel.core.task.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import gabriel.core.project.domain.Project;
import gabriel.core.user.domain.User;

public final class Task {

    /**
     * Builder class
     */
    public static final class Builder {

        private final UUID id;
        private final String description;
        private final LocalDateTime createdAt;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private final User createdBy;
        private User takenBy;
        private Project project;
        private TaskState state;

        /**
         * Constructor
         *
         * @param id
         * @param description
         * @param createdAt
         * @param createdBy
         */
        public Builder(UUID id, String description, LocalDateTime createdAt, User createdBy) {
            this.id = id;
            this.description = description;
            if (createdAt != null) {
                this.createdAt = createdAt;
            } else {
                this.createdAt = LocalDateTime.now();
            }
            this.createdBy = createdBy;
            this.state = TaskState.AVAILABLE;
        }

        public Builder withStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withProject(Project project) {
            this.project = project;
            return this;
        }

        public Builder withState(TaskState state) {
            this.state = state;
            return this;
        }

        public Task build() {
            return new Task(this);
        }

    }

    private final UUID id;
    private final String description;
    private final LocalDateTime createdAt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private final User createdBy;
    private User takenBy;
    private Project project;

    private TaskState state;

    private Task(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.createdBy = builder.createdBy;
        this.project = builder.project;
        this.takenBy = builder.takenBy;
        this.state = builder.state;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public User getTakenBy() {
        return takenBy;
    }

    public Project getProject() {
        return project;
    }

    public TaskState getState() {
        return state;
    }

    /**
     * hashCode and equals to: id
     */
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

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setTakenBy(User takenBy) {
        this.takenBy = takenBy;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

}
