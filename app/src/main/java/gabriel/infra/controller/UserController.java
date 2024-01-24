package gabriel.infra.controller;

import gabriel.core.UseCaseDto;
import gabriel.core.user.dto.SignupDto;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signup;
import gabriel.infra.util.JsonDeserialize;
import gabriel.infra.util.ResponseError;

// /user/signup?{"username":"gabriel","password":"123","workingHours":"10"}

public class UserController {

    private final UserRepository userRepository;
    private final JsonDeserialize deserialize;

    public UserController(UserRepository userRepository, JsonDeserialize deserialize) {
        this.userRepository = userRepository;
        this.deserialize = deserialize;
    }

    public UseCaseDto.Output signup(String body) {
        SignupDto.Input input = deserialize.executeTest(SignupDto.Input.class, body);

        input.setUserRepository(userRepository);

        Signup signup = new Signup(input);

        try {
            SignupDto.Output output = signup.execute();
            return output;
        } catch (Exception e) {
            return new ResponseError(e.getMessage(), "400");
        }
    }

    public UseCaseDto.Output signin(String body) {
        return null;
        // JsonDeserialize<String, String> deserialize = new JsonDeserializeImpl<>();

        // Map<String, String> entries = deserialize.execute(body);

        // SignupDto.Input input = new SignupDto.Input(userRepository,
        // entries.get("username"), entries.get("password"),
        // entries.get("name"), 0);

        // Signup signup = new Signup(input);

        // try {
        // SignupDto.Output output = signup.execute();
        // return output;
        // } catch (Exception e) {
        // return new ResponseError(e.getMessage(), "400");
        // }
    }

}
