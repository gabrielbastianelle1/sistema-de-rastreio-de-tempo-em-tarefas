package gabriel.core.user.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.user.domain.User;
import gabriel.core.user.repository.UserRepository;

public final class SigninDto {
    public static final class Input implements UseCaseDto.Input {
        private String username;
        private String password;
        private UserRepository userRepository;

        public Input() {

        }

        public String username() {
            return username;
        }

        public String password() {
            return password;
        }

        public UserRepository userRepository() {
            return userRepository;
        }

    }

    public static final record Output(User user) implements UseCaseDto.Output {
    }

}
