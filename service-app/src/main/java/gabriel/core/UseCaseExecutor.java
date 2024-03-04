package gabriel.core;

public class UseCaseExecutor {
    public static <I extends UseCaseDto.Input, O extends UseCaseDto.Output> O execute(
            UseCaseAbstraction<I, O> useCase) {
        return useCase.execute();
    };
}
