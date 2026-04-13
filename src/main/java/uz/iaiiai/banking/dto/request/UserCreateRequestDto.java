package uz.iaiiai.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
public class CreateUserRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
