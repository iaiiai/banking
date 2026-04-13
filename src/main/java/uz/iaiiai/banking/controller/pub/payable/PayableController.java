package uz.iaiiai.banking.controller.pub.payable;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.iaiiai.banking.dto.response.PayableResponseDto;
import uz.iaiiai.banking.service.PayableService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/payable")
public class PayableController {

    private final PayableService payableService;

    @GetMapping
    List<PayableResponseDto> getPayables(
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ) {
        return payableService.getPayables(page, size);
    }

    @GetMapping("/{alias}")
    PayableResponseDto getPayable(@PathVariable String alias) {
        return payableService.getPayable(alias);
    };
}
