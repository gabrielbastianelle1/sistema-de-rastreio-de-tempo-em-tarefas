package gabriel.infra.util;

public class Request {

    private String controllerName;
    private String methodName;
    private String body;

    public Request(String url) {
        /**
         * Possibles urls:
         * /controller/method
         * /controller/method?{"param":"value"}
         */
        String[] splitedUrl = url.replaceFirst("/", "").split("[?]");

        String[] controllerAndMethod = splitedUrl[0].split("/");

        this.controllerName = Character.toUpperCase(controllerAndMethod[0].charAt(0))
                + controllerAndMethod[0].substring(1)
                + "Controller";

        this.methodName = controllerAndMethod[1];

        if (splitedUrl.length > 1) {
            this.body = splitedUrl[1];
        }

    }

    public String getControllerName() {
        return controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getBody() {
        return body;
    }

}
