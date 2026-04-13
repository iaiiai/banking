package uz.iaiiai.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserAuthDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
