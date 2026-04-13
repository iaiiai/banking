package uz.iaiiai.banking.controller.admin.payable;

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
@RequestMapping("/payable")
@PreAuthorize("hasRole('ADMIN')")
public class PayableAdminController {
    private final PayableService payableService;

    @GetMapping
    List<PayableResponseDto> getPayablesAsAdmin(
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ) {
        return payableService.getPayablesAsAdmin(page, size);
    }

    @PostMapping
    PayableResponseDto createPayableAsAdmin(
            @Valid
            @RequestBody
            PayableRequestDto
                    dto
    ) {
        return payableService.createPayableAsAdmin(dto);
    }

    @PatchMapping("/{alias}")
    PayableStatusUpdateResponseDto setPayableStatusAsAdmin(
            @PathVariable
            String
                    alias,
            @RequestBody
            @Valid
            PayableStatusUpdateRequestDto
                    dto
    ) {
        return payableService.setPayableStatusAsAdmin(alias, dto);
    }

}
