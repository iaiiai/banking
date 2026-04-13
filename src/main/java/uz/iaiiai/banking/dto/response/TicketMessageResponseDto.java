package uz.iaiiai.banking.dto.response;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class TicketMessageResponseDto {
    private Long id;
    private UserResponseDto sender;
    private String content;
}
