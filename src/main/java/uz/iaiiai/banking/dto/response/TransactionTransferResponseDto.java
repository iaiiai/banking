package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.iaiiai.banking.model.entity.Wallet;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransferResponseDto {
    private final WalletResponseDto sender;
    private final WalletResponseDto recipient;
    private final BigDecimal amount;
}
