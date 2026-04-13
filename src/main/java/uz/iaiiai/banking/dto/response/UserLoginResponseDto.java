package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthResponseDto {
    private final String accessToken;
}
