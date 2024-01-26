package gabriel.infra.parseObject;

import java.util.Map;

public interface JsonMapper {
    public abstract Map<String, String> map(String json);
}
