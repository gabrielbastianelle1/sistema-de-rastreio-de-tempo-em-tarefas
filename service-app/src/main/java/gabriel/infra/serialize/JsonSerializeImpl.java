package gabriel.infra.serialize;

import java.lang.reflect.Field;

import gabriel.infra.util.JsonField;

public class JsonSerializeImpl implements JsonSerialize {

    @Override
    public String execute(Object dto) {
        Class<?> clazz = dto.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder jsonBuilder = new StringBuilder("{");

        for (Field field : fields) {
            /**
             * If annotation JsonFiel was set to false, the field is skipped
             */
            if (!field.getDeclaredAnnotation(JsonField.class).value()) {
                continue;
            }

            try {
                field.setAccessible(true);

                String fieldName = field.getName();
                Object fieldValue = field.get(dto);

                jsonBuilder.append("\"").append(fieldName).append("\":");

                if (fieldValue == null) {
                    jsonBuilder.append("null");
                } else if (field.getType().isPrimitive() || fieldValue instanceof Number
                        || fieldValue instanceof Boolean) {
                    jsonBuilder.append(fieldValue);
                } else if (fieldValue instanceof String) {
                    jsonBuilder.append("\"").append(fieldValue).append("\"");
                } else {
                    jsonBuilder.append(execute(fieldValue)); // Recursively transform nested objects
                }

                jsonBuilder.append(",");

            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }

            field.setAccessible(false);
        }

        if (jsonBuilder.charAt(jsonBuilder.length() - 1) == ',') {
            jsonBuilder.setLength(jsonBuilder.length() - 1); // Remove the trailing comma
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

}
