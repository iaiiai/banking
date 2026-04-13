package uz.iaiiai.banking.controller.admin.transaction;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.iaiiai.banking.dto.response.TransactionResponseDto;
import uz.iaiiai.banking.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
@PreAuthorize("hasRole('ADMIN')")
public class TransactionAdminController {
    private final TransactionService transactionService;

    @GetMapping
    List<TransactionResponseDto> getTransactionsInPeriodAsAdmin(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to,

            @RequestParam
            String username
    ) {
        return transactionService.getTransactionsInPeriodAsAdmin(from, to, username);
    }
}
