package gabriel.infra.myspring;

import java.lang.reflect.Field;

public class DependencyInjector {
    public static void inject(BeanContainer container) {
        container.getBeans().forEach((clazz, instance) -> {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    try {
                        Object dependency = container.getBean(field.getType());
                        field.set(instance, dependency);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
