package gabriel.infra.parse;

import java.lang.reflect.Field;
import java.util.Map;

import gabriel.infra.reflection.Container;

public class ExecuteParse {
    private Class<?> clazz;
    private Map<String, String> map;
    private Container container = new Container();

    public <T> ExecuteParse(Class<T> clazz, Map<String, String> map) {
        this.clazz = clazz;
        this.map = map;
    }

    @SuppressWarnings("unchecked")
    public <T> T execute() {
        try {
            Object dto = container.getInstance(clazz);

            for (Map.Entry<String, String> entry : this.map.entrySet()) {
                String fieldName = entry.getKey().trim();
                String fieldValue = entry.getValue().trim();

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
