package gabriel.infra.parse;

import java.util.Map;

public class MapperExecuter {
    public static Map<String, String> execute(JsonMapper mapper, String json) {
        return mapper.map(json);
    }
}
