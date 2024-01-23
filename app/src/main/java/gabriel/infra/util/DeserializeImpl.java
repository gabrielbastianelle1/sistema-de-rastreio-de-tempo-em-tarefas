package gabriel.infra.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DeserializeImpl<K, V> implements JsonDeserialize<K, V> {

    // @Override
    // public Map<K, V> execute(String json) {
    // String[] entries = json.replaceAll("[{}\"]", "").split(",");

    // Map<K, V> map = Arrays.asList(entries).stream()
    // .collect(Collectors.toMap(s -> s.split(":")[0], s -> s.split(":")[1]));

    // return map;
    // }

    @Override
    public Map<K, V> execute(String json) {
        String[] entries = json.replaceAll("[{}\"]", "").split(",");

        /**
         * entries - [username:gabriel, password:123]
         * entries.map - [[username, gabriel], [password, 123]]
         * entries.collect - map: entry<username, gabriel> entry<password, 123>
         */

        Map<K, V> map = Arrays.stream(entries)
                .map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        entry -> convert(entry[0]),
                        entry -> convert(entry[1])));

        return map;
    }

    @SuppressWarnings("unchecked")
    private <T> T convert(String str) {
        // Add your own logic here to convert string 'str' to type 'T'
        // For simplicity, assuming both K and V are strings in this example
        return (T) str;
    }

}
