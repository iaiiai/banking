package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.iaiiai.banking.model.enumeration.TicketStatus;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketResponseDto {
    private final Long id;
    private final UserResponseDto user;
    private TicketStatus status;
    private String subject;
    private List<TicketMessageResponseDto> messages;
}
