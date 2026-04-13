package uz.iaiiai.banking.service;

import uz.iaiiai.banking.dto.request.TransactionDepositRequestDto;
import uz.iaiiai.banking.dto.request.TransactionP2PRequestDto;
import uz.iaiiai.banking.dto.request.TransactionPayableRequestDto;
import uz.iaiiai.banking.dto.response.TransactionDepositResponseDto;
import uz.iaiiai.banking.dto.response.TransactionPayableResponseDto;
import uz.iaiiai.banking.dto.response.TransactionResponseDto;
import uz.iaiiai.banking.dto.response.TransactionTransferResponseDto;

import java.time.LocalDateTime;
import java.util.List;


public interface TransactionService {
    TransactionTransferResponseDto createP2PTransaction(TransactionP2PRequestDto request, Long senderId);
    TransactionPayableResponseDto createPayableTransaction(TransactionPayableRequestDto request, Long senderId);
    TransactionDepositResponseDto createDepositTransaction(TransactionDepositRequestDto request, Long recipientId);
    List<TransactionResponseDto> getTransactionsInPeriodAsAdmin(LocalDateTime from, LocalDateTime to, String username);
    List<TransactionResponseDto> getTransactionsInPeriod(LocalDateTime from, LocalDateTime to, String username);
}
