package uz.iaiiai.banking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionDepositRequestDto {
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final BigDecimal amount;
}
