package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class DepositResponseDto {
    private final WalletResponseDto recipient;
    private final BigDecimal amount;
}
