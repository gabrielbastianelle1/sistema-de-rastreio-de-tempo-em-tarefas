package gabriel.infra.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private final Container container;

    public ObjectFactory() {
        container = new Container();
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(Map<String, String> map, Class<T> clazz) {
        try {
            Object object = container.getInstance(clazz);

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String fieldName = entry.getKey().trim();
                String fieldValue = entry.getValue().trim();

                Field field = clazz.getDeclaredField(fieldName);

                field.setAccessible(true);

                if (field.getType() == String.class) {
                    field.set(object, fieldValue);
                } else if (field.getType() == int.class) {
                    field.set(object, Integer.parseInt(fieldValue));
                } else if (field.getType() == Boolean.class) {
                    field.set(object, fieldValue);
                } else {
                    Class<?> nestedClass = field.getType();

                    Map<String, String> m = new HashMap<>();
                    m.put(fieldName, fieldValue);

                    Object nestedObject = execute(m, nestedClass);
                    field.set(object, nestedObject);
                }

                field.setAccessible(false);
            }

            return (T) object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T, K extends T> void register(Class<T> typeInterface, Class<K> typeInstace) {
        this.container.register(typeInterface, typeInstace);
    }
}
