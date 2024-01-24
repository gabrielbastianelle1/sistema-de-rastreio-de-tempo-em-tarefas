package gabriel.core.user.dto;

import gabriel.core.UseCaseDto;
import gabriel.core.user.domain.User;
import gabriel.core.user.repository.UserRepository;
import gabriel.infra.util.JsonField;

public final class SignupDto {
    public static final class Input implements UseCaseDto.Input {
        private UserRepository userRepository;
        private String username;
        private String password;
        private String name;
        private int workingHours;

        public Input() {

        }

        public Input(UserRepository userRepository, String username, String password, String name, int workingHours) {
            this.userRepository = userRepository;
            this.username = username;
            this.password = password;
            this.name = name;
            this.workingHours = workingHours;
        }

        public void setUserRepository(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public UserRepository userRepository() {
            return userRepository;
        }

        public String username() {
            return username;
        }

        public String password() {
            return password;
        }

        public String name() {
            return name;
        }

        public int workingHours() {
            return workingHours;
        }
    }

    public static final record Output(@JsonField(true) User user) implements UseCaseDto.Output {
    }
}
