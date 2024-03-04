package gabriel.infra.parse;

public interface JsonParse {
    public abstract <T> T execute(String json, Class<T> clazz);
}
