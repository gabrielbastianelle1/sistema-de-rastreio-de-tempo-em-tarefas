package gabriel.core.user.usecases;

import gabriel.core.user.domain.User;
import gabriel.core.user.interfaces.EditAccountAbstraction;

public class EditAccount extends EditAccountAbstraction {

    public EditAccount(User user) {
        super(user);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void editName(String newName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editName'");
    }

    @Override
    public void editEditWorkingHour(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editEditWorkingHour'");
    }

    // public EditAccount(User user) {
    // super(user);
    // }

    // @Override
    // public void editName(String newName) {
    // user.setName(newName);
    // }

    // @Override
    // public void editEditWorkingHour(int i) {
    // user.setWorkingHours(i);
    // }

}
