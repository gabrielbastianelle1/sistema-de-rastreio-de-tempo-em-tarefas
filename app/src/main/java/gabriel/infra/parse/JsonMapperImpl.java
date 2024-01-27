package gabriel.infra.parse;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonMapperImpl implements JsonMapper {

    @Override
    public Map<String, String> map(String json) {
        String[] entries = json.replaceAll("[{}\"]", "").split(",");
        Map<String, String> map = Arrays.stream(entries)
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        entry -> entry[0],
                        entry -> entry[1]));
        return map;
    }

}
