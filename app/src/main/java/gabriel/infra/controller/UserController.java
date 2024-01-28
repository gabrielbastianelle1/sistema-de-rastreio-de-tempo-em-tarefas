package gabriel.infra.controller;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.UseCaseDto;
import gabriel.core.user.dto.SigninDto;
import gabriel.core.user.dto.SignupDto;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signin;
import gabriel.core.user.usecases.Signup;
import gabriel.infra.parse.JsonParse;
import gabriel.infra.util.Response;
import gabriel.infra.util.ResponseError;

// /user/signup?{"username":"gabriel","password":"123","workingHours":"10"}
// /user/signin?{"username":"gabriel","password":"123"}
// nc 172.18.0.2 4000

public class UserController {

    private final UserRepository userRepository;
    private final JsonParse deserialize;

    public UserController(UserRepository userRepository, JsonParse deserialize) {
        this.userRepository = userRepository;
        this.deserialize = deserialize;
    }

    public <I extends UseCaseDto.Input, O extends UseCaseDto.Output> UseCaseDto.Output executeUseCase(
            UseCaseAbstraction<I, O> useCase, String message) {

        try {
            UseCaseDto.Output output = useCase.execute();
            UseCaseDto.Output response = new Response(output, "200", message);
            return response;
        } catch (Exception e) {
            return new ResponseError(e.getMessage(), "400");
        }
    };

    public UseCaseDto.Output signup(String body) {
        SignupDto.Input input = deserialize.execute(body, SignupDto.Input.class);

        input.setUserRepository(userRepository);

        Signup signup = new Signup(input);
        return executeUseCase(signup, "success signup");
    }

    public UseCaseDto.Output signin(String body) {
        SigninDto.Input input = deserialize.execute(body, SigninDto.Input.class);

        input.setUserRepository(userRepository);

        Signin signin = new Signin(input);
        return executeUseCase(signin, "success signin");
    }

}
