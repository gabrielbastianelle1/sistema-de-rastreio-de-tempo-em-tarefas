package gabriel.infra.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import gabriel.infra.reflection.Container;

public class JsonDeserializeImpl implements JsonDeserialize {
    private Container container = new Container();

    // @Override
    // public Map<K, V> execute(String json) {
    // String[] entries = json.replaceAll("[{}\"]", "").split(",");

    // Map<K, V> map = Arrays.asList(entries).stream()
    // .collect(Collectors.toMap(s -> s.split(":")[0], s -> s.split(":")[1]));

    // return map;
    // }

    @Override
    public <K, V> Map<K, V> execute(String json) {
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

    @Override
    @SuppressWarnings("unchecked")
    public <T> T executeTest(Class<T> clazz, String json) {

        try {
            String[] entries = json.replaceAll("[{}\"]", "").split(",");
            Object dto = container.getInstance(clazz);

            for (String pair : entries) {
                String[] keyValue = pair.split(":");
                String fieldName = keyValue[0].trim();
                String fieldValue = keyValue[1].trim();

                Field field = clazz.getDeclaredField(fieldName);

                field.setAccessible(true);
                if (field.getType() == String.class) {
                    field.set(dto, fieldValue);
                } else if (field.getType() == int.class) {
                    field.set(dto, Integer.parseInt(fieldValue));
                }

                field.setAccessible(false);
            }

            return (T) dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
