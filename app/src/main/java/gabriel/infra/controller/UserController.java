package gabriel.infra.controller;

import gabriel.core.UseCaseAbstraction;
import gabriel.core.UseCaseDto;
import gabriel.core.user.dto.SigninDto;
import gabriel.core.user.dto.SignupDto;
import gabriel.core.user.repository.UserRepository;
import gabriel.core.user.usecases.Signin;
import gabriel.core.user.usecases.Signup;
import gabriel.infra.parseObject.JsonDeserialize;
import gabriel.infra.parseObject.JsonMapper;
import gabriel.infra.util.Response;
import gabriel.infra.util.ResponseError;

// /user/signup?{"username":"gabriel","password":"123","workingHours":"10"}
// /user/signin?{"username":"gabriel","password":"123"}
// nc 172.18.0.2 4000

public class UserController {

    private final UserRepository userRepository;
    private final JsonDeserialize deserialize;
    private final JsonMapper jsonMapper;

    public UserController(UserRepository userRepository, JsonDeserialize deserialize, JsonMapper jsonMapper) {
        this.userRepository = userRepository;
        this.deserialize = deserialize;
        this.jsonMapper = jsonMapper;
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
        SignupDto.Input input = deserialize.defineClass(SignupDto.Input.class).defineString(body)
                .mapToPairs(s -> jsonMapper.map(s)).execute();

        input.setUserRepository(userRepository);

        Signup signup = new Signup(input);
        return executeUseCase(signup, "success signup");
    }

    public UseCaseDto.Output signin(String body) {
        SigninDto.Input input = deserialize.defineClass(SigninDto.Input.class).defineString(body)
                .mapToPairs(s -> jsonMapper.map(s)).execute();

        input.setUserRepository(userRepository);

        Signin signin = new Signin(input);
        return executeUseCase(signin, "success signin");
    }

}
