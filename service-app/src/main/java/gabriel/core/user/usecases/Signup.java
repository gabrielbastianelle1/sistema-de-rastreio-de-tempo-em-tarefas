package gabriel.core.user.usecases;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.dto.SignupDto;
import gabriel.core.user.dto.SignupDto.Input;
import gabriel.core.user.dto.SignupDto.Output;
import gabriel.core.user.exceptions.UsernameTakenException;

public final class Signup extends UseCaseAbstraction<SignupDto.Input, SignupDto.Output> {

    public Signup(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        if (input.userRepository().findById(new Username(input.username())) != null)
            throw new UsernameTakenException();

        User.Builder user = new User.Builder(new Username(input.username()), input.password());

        if (input.name() != null) {
            user.withName(input.name());
        }

        if (input.workingHours() != 0) {
            user.withWorkingHours(input.workingHours());
        }

        input.userRepository().save(user.build());

        return new Output(user.build());
    }

}
