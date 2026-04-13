package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.iaiiai.banking.model.entity.Wallet;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private WalletResponseDto wallet;
    private String role;
}
