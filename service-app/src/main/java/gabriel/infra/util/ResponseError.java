package gabriel.infra.util;

import gabriel.core.UseCaseDto;

public class ResponseError implements UseCaseDto.Output {
    @JsonField(true)
    private final String message;

    @JsonField(true)
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
