package gabriel.core.invitation.dto;

import gabriel.core.UseCaseDto;

public interface UpdateInvitationDto {

    /**
     * Input
     */
    public record Input() implements UseCaseDto.Input {
    }

    /**
     * Output
     */
    public record Output() implements UseCaseDto.Output {
    }

}
