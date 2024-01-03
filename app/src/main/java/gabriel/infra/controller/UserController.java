package gabriel.infra.controller;

import java.util.Map;

import gabriel.core.user.dto.SignupDto;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signup;
import gabriel.infra.util.DeserializeImpl;
import gabriel.infra.util.JsonDeserialize;
import gabriel.repository.Database;

public class UserController {

    private final UserRepository userRepository = new Database();

    public void signup() {
        // JsonDeserialize<String, String> deserialize = new DeserializeImpl<>();

        // Map<String, String> entries = deserialize.execute(body);

        // SignupDto.Input input = new SignupDto.Input(userRepository,
        // entries.get("username"), body, body, 0);
        // Signup signup = new Signup(input);

    }

}
