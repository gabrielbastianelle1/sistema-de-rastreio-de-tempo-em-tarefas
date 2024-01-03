package gabriel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ControllerHandler {

    private final String urlClass;

    public ControllerHandler(String urlClass) {
        this.urlClass = urlClass;
    }

    public ObjectHandler refletClass() {
        try {
            Class<?> controllerClazz = Class.forName(urlClass);
            return new ObjectHandler(controllerClazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public class ObjectHandler {

        private final Class<?> controllerClazz;

        public ObjectHandler(Class<?> controllerClazz) {
            this.controllerClazz = controllerClazz;
        }

        public MethodHandler createNewInstance() {
            try {
                return new MethodHandler(this.controllerClazz.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

        public Constructor<?> getConstructor() {
            try {
                return this.controllerClazz.getDeclaredConstructor();
            } catch (IllegalArgumentException
                    | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public class MethodHandler {

        private final Object controllerObject;

        public MethodHandler(Object controllerObject) {
            this.controllerObject = controllerObject;
        }

        public Object executeMethod(String methodName) {
            try {
                return this.controllerObject.getClass().getDeclaredMethod(methodName).invoke(this.controllerObject);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
