package gabriel.infra.parse;

import java.util.Map;

public interface Mapper<K, V, T> {
    public abstract Map<K, V> execute(T input);
}
