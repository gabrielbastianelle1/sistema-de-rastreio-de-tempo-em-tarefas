package gabriel.infra.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Container {
    private final Map<Class<?>, Class<?>> typeMap = new HashMap<>();

    public Object getInstance(Class<?> clazz) {
        Class<?> destinyType = typeMap.get(clazz);

        if (destinyType != null) {
            return getInstance(destinyType);
        }

        Stream<Constructor<?>> constructors = Stream.of(clazz.getDeclaredConstructors());

        Optional<Constructor<?>> baseConstructor = constructors.filter(c -> c.getParameterCount() == 0).findFirst();

        try {
            if (baseConstructor.isPresent()) {
                Object instance = baseConstructor.get().newInstance();
                return instance;
            }

            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

            List<Object> params = new ArrayList<>();

            for (Parameter param : constructor.getParameters()) {
                Class<?> paramType = param.getType();
                params.add(getInstance(paramType));
            }

            return constructor.newInstance(params.toArray());

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public <T, K extends T> void register(Class<T> typeInterface, Class<K> typeInstace) {
        this.typeMap.put(typeInterface, typeInstace);
    }
}
