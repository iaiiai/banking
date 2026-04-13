package uz.iaiiai.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PayableStatusUpdateResponseDto {
    private final Long id;
    private final String alias;
    private final boolean isActive;
}
