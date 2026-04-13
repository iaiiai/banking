package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ApiErrorDto {
    private String timestamp;
    private String error;
    private String path;
}
