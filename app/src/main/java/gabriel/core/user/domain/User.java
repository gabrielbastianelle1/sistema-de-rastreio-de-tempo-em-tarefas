package gabriel.core.user.domain;

public class User {
    private Username username;
    private String password;
    private String name;

    public User(String username, String password, String name) {
        this.username = new Username(username);
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{username=" + username + ", password=" + password + ", name=" + name + "}";
    }

    public String getName() {
        return name;
    }

    public Username getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

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

}
