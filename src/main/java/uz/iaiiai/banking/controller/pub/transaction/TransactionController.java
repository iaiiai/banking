package uz.iaiiai.banking.controller.transaction;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.iaiiai.banking.dto.request.TransactionDepositRequestDto;
import uz.iaiiai.banking.dto.request.TransactionP2PRequestDto;
import uz.iaiiai.banking.dto.request.TransactionPayableRequestDto;
import uz.iaiiai.banking.dto.response.TransactionDepositResponseDto;
import uz.iaiiai.banking.dto.response.TransactionPayableResponseDto;
import uz.iaiiai.banking.dto.response.TransactionResponseDto;
import uz.iaiiai.banking.dto.response.TransactionTransferResponseDto;
import uz.iaiiai.banking.security.CustomUserDetails;
import uz.iaiiai.banking.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transaction/p2p")
    public TransactionTransferResponseDto handleP2P(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid TransactionP2PRequestDto dto) {
        return transactionService.createP2PTransaction(dto, userDetails.getId());
    }

    @PostMapping("/transaction/payable")
    public TransactionPayableResponseDto handlePayable(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody TransactionPayableRequestDto dto) {
        return transactionService.createPayableTransaction(dto, userDetails.getId());
    }

    @PostMapping("/transaction/deposit")
    public TransactionDepositResponseDto handleDeposit(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid TransactionDepositRequestDto dto) {
        return transactionService.createDepositTransaction(dto, userDetails.getId());
    }

    @GetMapping("/transaction")
    public List<TransactionResponseDto> handleAllFilteredBetweenTime(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to,

            @AuthenticationPrincipal
            CustomUserDetails auth
    ) {
        String username = auth.getUsername();
        return transactionService.getByTimestampBetween(from, to, username);
    }
}
