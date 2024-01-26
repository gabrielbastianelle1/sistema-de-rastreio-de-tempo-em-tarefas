package gabriel.core.user.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.user.domain.User;
import gabriel.core.user.repository.UserRepository;
import gabriel.infra.util.JsonField;

public final class SigninDto {
    public static final class Input implements UseCaseDto.Input {
        private String username;
        private String password;
        private UserRepository userRepository;

        public Input() {

        }

        public Input(UserRepository userRepository, String username, String password) {
            this.userRepository = userRepository;
            this.username = username;
            this.password = password;
        }

        public void setUserRepository(UserRepository userRepository) {
            this.userRepository = userRepository;
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

    public static final record Output(@JsonField(true) User user) implements UseCaseDto.Output {
    }

}
