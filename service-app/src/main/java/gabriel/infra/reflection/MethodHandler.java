package gabriel.infra.reflection;

import java.lang.reflect.InvocationTargetException;

import gabriel.core.UseCaseDto;

public class MethodHandler {
    private final Object controllerObject;

    public MethodHandler(Object controllerObject) {
        this.controllerObject = controllerObject;
    }

    public UseCaseDto.Output executeMethod(String methodName, String body) {
        try {
            if (body != null) {

                return (UseCaseDto.Output) this.controllerObject.getClass()
                        .getDeclaredMethod(methodName, String.class)
                        .invoke(
                                this.controllerObject,
                                body);
            }
            return (UseCaseDto.Output) this.controllerObject.getClass().getDeclaredMethod(methodName)
                    .invoke(this.controllerObject);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}