package uz.iaiiai.banking.controller.payable;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.iaiiai.banking.dto.request.PayableRequestDto;
import uz.iaiiai.banking.dto.request.PayableStatusUpdateRequestDto;
import uz.iaiiai.banking.dto.response.PayableResponseDto;
import uz.iaiiai.banking.dto.response.PayableStatusUpdateResponseDto;
import uz.iaiiai.banking.service.PayableService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PayableController {

    private final PayableService payableService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/payable")
    PayableResponseDto handleCreate(
            @Valid
            @RequestBody
            PayableRequestDto
            dto
    ) {
        return payableService.createPayable(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/payable/{alias}")
    PayableStatusUpdateResponseDto handlePayableStatusUpdate(
            @PathVariable
            String
            alias,
            @RequestBody
            @Valid
            PayableStatusUpdateRequestDto
            dto
    ) {
        return payableService.updatePayableStatus(alias, dto);
    }

    @GetMapping("/payable/{alias}")
    PayableResponseDto get(@PathVariable String alias) {
        return payableService.get(alias);
    };

    @GetMapping("/payable")
    List<PayableResponseDto> getAll(
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ) {
        return payableService.getAll(page, size);
    }
}
