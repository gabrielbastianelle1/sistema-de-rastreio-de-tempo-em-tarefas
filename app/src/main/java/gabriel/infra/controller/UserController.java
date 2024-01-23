package gabriel.infra.controller;

import java.util.Map;

import gabriel.core.UseCaseDto;
import gabriel.core.user.dto.SignupDto;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signup;
import gabriel.infra.repository.Database;
import gabriel.infra.util.DeserializeImpl;
import gabriel.infra.util.JsonDeserialize;
import gabriel.infra.util.ResponseError;

// /user/signup?{"username":"gabriel","password":"123"}

public class UserController {

    private final UserRepository userRepository = new Database();

    public UseCaseDto.Output signup(String body) {
        JsonDeserialize<String, String> deserialize = new DeserializeImpl<>();

        Map<String, String> entries = deserialize.execute(body);

        SignupDto.Input input = new SignupDto.Input(userRepository, entries.get("username"), entries.get("password"),
                entries.get("name"), 0);

        Signup signup = new Signup(input);

        try {
            SignupDto.Output output = signup.execute();
            return output;
        } catch (Exception e) {
            return new ResponseError(e.getMessage(), "400");
        }
    }

}
