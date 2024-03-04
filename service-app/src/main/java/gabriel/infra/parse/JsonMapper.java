package gabriel.infra.parse;

import java.util.Map;

public interface JsonMapper {
    public abstract Map<String, String> execute(String json);
}
