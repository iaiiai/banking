package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WalletResponseDto {
    private final Long id;
    private final BigDecimal balance;
}
