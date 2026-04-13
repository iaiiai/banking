package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
public class TransactionPayableResponseDto extends TransactionResponseDto {
    private PayableResponseDto payable;
}
