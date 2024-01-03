package gabriel.core.user.domain;

public class Username {
    private final String username;
    private final String regex = "^(?=.*[a-zA-Z])[a-zA-Z0-9_]{3,16}$";

    public Username(String username) {
        if (username.matches(regex) == false)
            throw new IllegalArgumentException(
                    "invalid username, it sould have no special caracters and length between 3 and 16");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /**
     * equals and hashcode to username field
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Username other = (Username) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return username;
    }
}
