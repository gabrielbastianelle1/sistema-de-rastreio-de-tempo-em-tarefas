package gabriel.infra.parseObject;

import java.util.Map;

public class MapperExecuter {
    public static Map<String, String> execute(JsonMapper mapper, String json) {
        return mapper.map(json);
    }
}
