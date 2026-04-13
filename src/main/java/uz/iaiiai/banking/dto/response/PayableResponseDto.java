package uz.iaiiai.banking.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class PayableResponseDto {
    private final Long id;
    private final String alias;
    private final String title;
    private final String description;
    private final BigDecimal price;
    private final String pictureUrl;
    private final boolean isActive;
}
