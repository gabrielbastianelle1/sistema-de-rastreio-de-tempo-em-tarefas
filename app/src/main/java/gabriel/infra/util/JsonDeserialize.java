package gabriel.infra.util;

import java.util.Map;

public interface JsonDeserialize<K, V> {
    public abstract Map<K, V> execute(String json);
}
