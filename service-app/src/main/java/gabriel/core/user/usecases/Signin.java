package gabriel.core.user.usecases;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.dto.SigninDto;
import gabriel.core.user.dto.SigninDto.Input;
import gabriel.core.user.dto.SigninDto.Output;
import gabriel.core.user.exceptions.UserNotFoundException;

public final class Signin extends UseCaseAbstraction<SigninDto.Input, SigninDto.Output> {

    public Signin(Input input) {
        super(input);
    }

    @Override
    public Output execute() {
        Username username = new Username(input.username());

        User user = input.userRepository().findById(username);
        if (user == null)
            throw new UserNotFoundException("username or password wrong");

        if (!user.getPassword().equals(input.password())) {
            throw new UserNotFoundException("username or password wrong");
        }

        return new Output(user);
    }

}
