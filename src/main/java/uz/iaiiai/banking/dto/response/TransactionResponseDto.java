package uz.iaiiai.banking.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransactionResponseDto {
    private final BigDecimal amount;
}
