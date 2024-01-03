package gabriel.core.user.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.user.domain.User;
import gabriel.core.user.repository.UserRepository;

public final class SigninDto {
    public static final record Input(UserRepository userRepository, String username, String password)
            implements UseCaseDto.Input {
    }

    public static final record Output(User user) implements UseCaseDto.Output {
    }

}
