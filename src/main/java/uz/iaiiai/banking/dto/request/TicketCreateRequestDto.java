package uz.iaiiai.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketCreateRequestDto {
    @NotBlank
    private String subject;
}
