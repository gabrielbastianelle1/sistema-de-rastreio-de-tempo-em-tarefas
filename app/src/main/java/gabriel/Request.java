package gabriel;

public class Request {

    private String controllerName;
    private String methodName;

    public Request(String url) {
        String[] splitedUrl = url.replaceFirst("/", "").split("/");
        this.controllerName = Character.toUpperCase(splitedUrl[0].charAt(0)) + splitedUrl[0].substring(1)
                + "Controller";

        this.methodName = splitedUrl[1];
    }

    public String getControllerName() {
        return controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

}
