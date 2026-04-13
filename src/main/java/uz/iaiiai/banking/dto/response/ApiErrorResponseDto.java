package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponseDto {
    private String timestamp;
    private String error;
    private String path;
}
