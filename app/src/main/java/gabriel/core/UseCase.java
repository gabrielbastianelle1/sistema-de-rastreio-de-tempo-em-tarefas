package gabriel.core;

public interface UseCase<Output> {
    public abstract Output execute();
}
