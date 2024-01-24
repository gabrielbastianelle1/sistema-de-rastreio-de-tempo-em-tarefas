package gabriel.infra.util;

import java.util.Map;

public interface JsonDeserialize {
    public abstract <K, V> Map<K, V> execute(String json);

    public abstract <T> T executeTest(Class<T> clazz, String json);

}
