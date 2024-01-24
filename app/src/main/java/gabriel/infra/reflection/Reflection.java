package gabriel.infra.reflection;

public class Reflection {
    public Class<?> getClass(String fqn) {
        Class<?> clazz;
        try {
            clazz = Class.forName(fqn);
            return clazz;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
