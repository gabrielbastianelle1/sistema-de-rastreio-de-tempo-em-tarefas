package gabriel.infra.myspring;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class ComponentScanner {
    public static void scan(String basePackage, BeanContainer container)
            throws IOException, ReflectiveOperationException, URISyntaxException {
        String path = basePackage.replace('.', '/');
        Path root = Paths.get(ClassLoader.getSystemClassLoader().getResource(path).toURI());

        Files.walk(root)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".class"))
                .forEach(file -> {
                    String relativePath = file.toString()
                            .replace(root.toString(), "")
                            .replace(File.separator, ".")
                            .replaceFirst("^\\.", "") // Remove leading "."
                            .replace(".class", "");

                    try {
                        // Attempt to load the class
                        Class<?> clazz = Class.forName(basePackage + "." + relativePath);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            Stream<Constructor<?>> constructors = Stream.of(clazz.getDeclaredConstructors());
                            Optional<Constructor<?>> baseConstructor = constructors
                                    .filter(c -> c.getParameterCount() == 0).findFirst();

                            if (baseConstructor.isPresent()) {
                                Constructor<?> constructor = baseConstructor.get();
                                constructor.setAccessible(true);
                                Object instance = constructor.newInstance();
                                constructor.setAccessible(false);
                                container.addBean(clazz, instance);
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        // Log or handle gracefully if the class cannot be loaded
                        System.err.println("Class not found: " + relativePath + ", error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to load class " + relativePath, e);
                    }
                });
    }
}
