package gabriel.infra.myspring;

import java.util.HashMap;
import java.util.Map;

public class BeanContainer {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public void addBean(Class<?> clazz, Object instance) {
        beans.put(clazz, instance);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        return (T) beans.get(clazz);
    }

    public Map<Class<?>, Object> getBeans() {
        return beans;
    }
}
