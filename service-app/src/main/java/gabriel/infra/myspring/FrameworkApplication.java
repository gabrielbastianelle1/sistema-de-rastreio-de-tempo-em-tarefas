package gabriel.infra.myspring;

import java.net.InetSocketAddress;

public class FrameworkApplication {

    static void createServer() {
    }

    public static void run(Class<?> mainClass) {
        try {
            Object mainInstance = mainClass.getConstructor().newInstance();
            BeanContainer container = new BeanContainer();
            container.addBean(mainClass, mainInstance);
            String basePackage = mainClass.getPackage().getName();
            ComponentScanner.scan(basePackage, container);
            DependencyInjector.inject(container);

            mainClass.getMethod("start").invoke(mainInstance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
