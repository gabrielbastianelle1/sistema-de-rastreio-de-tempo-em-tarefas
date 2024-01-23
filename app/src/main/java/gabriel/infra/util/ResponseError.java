package gabriel.infra.util;

import gabriel.core.UseCaseDto;

public class ResponseError implements UseCaseDto.Output {
    private final String message;
    private final String status;

    public ResponseError(String message, String status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseError [message=" + message + ", status=" + status + "]";
    }

}
