package uz.iaiiai.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayableStatusUpdateRequestDto {
    @NotNull
    private boolean active;
}
