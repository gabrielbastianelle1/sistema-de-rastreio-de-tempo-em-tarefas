package gabriel.core.user.interfaces;

import gabriel.core.user.domain.User;

public abstract class EditAccountAbstraction {

    protected final User user;

    public EditAccountAbstraction(User user) {
        this.user = user;
    }

    public abstract void editName(String newName);

    public User getUser() {
        return user;
    }

    public abstract void editEditWorkingHour(int i);

}
