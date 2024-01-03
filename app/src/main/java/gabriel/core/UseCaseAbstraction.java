package gabriel.core;

public abstract class UseCaseAbstraction<I extends UseCaseDto.Input, O extends UseCaseDto.Output> {

    protected final I input;

    public UseCaseAbstraction(I input) {
        this.input = input;
    }

    public abstract O execute();

}
