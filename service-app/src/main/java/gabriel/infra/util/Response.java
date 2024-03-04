package gabriel.infra.util;

import gabriel.core.UseCaseDto;

public class Response implements UseCaseDto.Output {
    @JsonField(true)
    private final UseCaseDto.Output output;

    @JsonField(true)
    private final String status;

    @JsonField(true)
    private final String message;

    public Response(UseCaseDto.Output output, String status, String message) {
        this.output = output;
        this.status = status;
        this.message = message;
    }

    public UseCaseDto.Output getOutput() {
        return output;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
