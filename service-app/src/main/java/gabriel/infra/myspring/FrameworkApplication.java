package gabriel.infra.myspring;

public class FrameworkApplication {
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
