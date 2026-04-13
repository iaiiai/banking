package uz.iaiiai.banking.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import uz.iaiiai.banking.model.enumeration.TransactionStatus;
import uz.iaiiai.banking.model.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class TransactionResponseDto {
    private final Long id;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final TransactionStatus transactionStatus;
    private final TransactionType transactionType;
    private final WalletResponseDto recipient;
    private final WalletResponseDto sender;
}
