package gabriel.infra.util;

public class Response<O> {
    private final O output;
    private final String status;
    private final String message;

    public Response(O output, String status, String message) {
        this.output = output;
        this.status = status;
        this.message = message;
    }

    public O getOutput() {
        return output;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{\"output\":" + output.toString() + ", \"status\":" + status + ", \"message\":" + message + "}";
    }

}
