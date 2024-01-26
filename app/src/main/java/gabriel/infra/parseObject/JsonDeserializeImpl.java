package gabriel.infra.parseObject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
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
    public <K, V> Map<K, V> a(String json) {
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
    public <T> T execute(Class<T> clazz, String stringToParse, Function<String, String[]> regexPatter) {

        try {
            String[] entries = regexPatter.apply(stringToParse);
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

    @Override
    public <T> ClassInput defineClass(Class<T> clazz) {
        return new ClassInput(clazz);
    }

}
