package gabriel.infra.parseObject;

import java.util.Map;
import java.util.function.Function;

public interface JsonDeserialize {
    public abstract <K, V> Map<K, V> a(String json);

    public abstract <T> T execute(Class<T> clazz, String stringToParse, Function<String, String[]> regexPatter);

    public abstract <T> ClassInput defineClass(Class<T> clazz);

}
