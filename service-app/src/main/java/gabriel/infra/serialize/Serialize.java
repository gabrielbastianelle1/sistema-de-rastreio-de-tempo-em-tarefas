package gabriel.infra.serialize;

public interface Serialize<I, O> {
    public abstract O execute(I input);
}
