package uz.iaiiai.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketMessageSendRequestDto {
    private String content;
}
