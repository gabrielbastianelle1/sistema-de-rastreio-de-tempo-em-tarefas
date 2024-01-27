package gabriel.infra.parse;

import java.util.Map;
import java.util.function.Function;

public interface JsonParse {
    public abstract <K, V> Map<K, V> a(String json);

    public abstract <T> T execute(Class<T> clazz, String stringToParse, Function<String, String[]> regexPatter);

    public abstract <T> ClassInput defineClass(Class<T> clazz);

}
