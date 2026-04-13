package uz.iaiiai.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class PayableTransactionRequestDto {
    private final String serviceAlias;
    private final BigDecimal amount;
}
