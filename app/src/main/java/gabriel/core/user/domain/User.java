package gabriel.core.user.domain;

import gabriel.infra.util.JsonField;

public final class User {

    public final static class Builder {
        private final Username username;
        private final String password;
        private String name;
        private int workingHours;

        public Builder(Username username, String password) {
            this.username = username;
            this.password = password;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withWorkingHours(int workingHours) {
            this.workingHours = workingHours;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    @JsonField(true)
    private final Username username;

    @JsonField(true)
    private final String password;

    @JsonField(true)
    private String name;

    @JsonField(true)
    private int workingHours;

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.name = builder.name;
        this.workingHours = builder.workingHours;
    }

    /**
     * equals and hashCode to Username field
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public Username getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getWorkingHours() {
        return workingHours;
    }

}