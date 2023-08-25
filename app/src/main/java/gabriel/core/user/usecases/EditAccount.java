package gabriel.core.user.usecases;

import gabriel.core.user.domain.User;
import gabriel.core.user.interfaces.EditAccountAbstraction;

public class EditAccount extends EditAccountAbstraction {

    public EditAccount(User user) {
        super(user);
    }

    @Override
    public void editName(String newName) {
        user.setName(newName);
        // throw new UnsupportedOperationException("Unimplemented method 'editName'");
    }

}