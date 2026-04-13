package uz.iaiiai.banking.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.iaiiai.banking.dto.request.TransactionDepositRequestDto;
import uz.iaiiai.banking.dto.request.TransactionP2PRequestDto;
import uz.iaiiai.banking.dto.request.TransactionPayableRequestDto;
import uz.iaiiai.banking.dto.response.*;
import uz.iaiiai.banking.exception.InsufficientBalanceException;
import uz.iaiiai.banking.exception.InvalidTransferRecipientException;
import uz.iaiiai.banking.exception.PayableNotFoundException;
import uz.iaiiai.banking.exception.RecipientNotFoundException;
import uz.iaiiai.banking.mapper.PayableMapper;
import uz.iaiiai.banking.mapper.TransactionMapper;
import uz.iaiiai.banking.mapper.WalletMapper;
import uz.iaiiai.banking.model.entity.Payable;
import uz.iaiiai.banking.model.entity.Transaction;
import uz.iaiiai.banking.model.entity.User;
import uz.iaiiai.banking.model.entity.Wallet;
import uz.iaiiai.banking.model.enumeration.TransactionStatus;
import uz.iaiiai.banking.model.enumeration.TransactionType;
import uz.iaiiai.banking.repository.PayableRepository;
import uz.iaiiai.banking.repository.TransactionRepository;
import uz.iaiiai.banking.repository.UserRepository;
import uz.iaiiai.banking.repository.WalletRepository;
import uz.iaiiai.banking.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    private final WalletMapper walletMapper;
    private final PayableRepository payableRepository;
    private final PayableMapper payableMapper;
    private final TransactionMapper transactionMapper;

    private Transaction createTransaction(Long senderWalletId, Long recipientWalletId, BigDecimal amount, TransactionType transactionType) {
        Wallet sender = walletRepository.findById(senderWalletId).orElseThrow(() -> new IllegalStateException("Wallet not found"));
        Wallet recipient = walletRepository.findById(recipientWalletId).orElseThrow(() -> new IllegalStateException("Wallet not found"));
        if (sender.getId().equals(recipient.getId())) throw new InvalidTransferRecipientException();
        if (sender.getBalance().compareTo(amount) <= 0) throw new InsufficientBalanceException();
        sender.setBalance(sender.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));
        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setSender(sender);
        tx.setRecipient(recipient);
        tx.setTransactionType(transactionType);
        tx.setTransactionStatus(TransactionStatus.COMPLETED);
        return transactionRepository.save(tx);
    }

    @Override
    @Transactional
    public TransactionTransferResponseDto createP2PTransaction(TransactionP2PRequestDto request, Long senderId) {
        String recipientUsername = request.getRecipientUsername();
        BigDecimal transferAmount = request.getAmount();
        TransactionType transactionType = TransactionType.P2P;
        User sender = userRepository.findById(senderId).orElseThrow(() -> new IllegalStateException("Transfer sender not found"));
        User recipient = userRepository.findUserByUsername(recipientUsername).orElseThrow(RecipientNotFoundException::new);
        Wallet senderWallet = walletRepository.findByUserId(senderId).orElseThrow(() -> new IllegalStateException("Transfer sender not found"));
        Wallet recipientWallet = recipient.getWallet();
        Transaction transaction = createTransaction(senderWallet.getId(), recipientWallet.getId(), transferAmount, transactionType);
        WalletResponseDto senderWalletDto = walletMapper.toDto(transaction.getSender());
        WalletResponseDto recipientWalletDto = walletMapper.toDto(transaction.getRecipient());
        return TransactionTransferResponseDto.builder().sender(senderWalletDto).recipient(recipientWalletDto).amount(transferAmount).build();
    }

    @Override
    @Transactional
    public TransactionPayableResponseDto createPayableTransaction(TransactionPayableRequestDto request, Long senderId) {
        Payable payable = payableRepository.findPayableByAliasAndIsActiveTrue(request.getAlias())
                .orElseThrow(PayableNotFoundException::new);
        BigDecimal transferAmount = payable.getPrice();
        TransactionType transactionType = TransactionType.PAYABLE;
        Wallet senderWallet = walletRepository.findByUserId(senderId).orElseThrow(() -> new IllegalStateException("Payable sender not found"));
        Wallet payableWallet = walletRepository.findWalletByPayable_Alias(payable.getAlias())
                .orElseThrow(PayableNotFoundException::new);
        Transaction transaction = createTransaction(senderWallet.getId(), payableWallet.getId(), transferAmount, transactionType);
        PayableResponseDto payableResponseDto = payableMapper.toDto(transaction.getRecipient().getPayable());
        return TransactionPayableResponseDto.builder()
                .payable(payableResponseDto)
                .amount(transaction.getAmount())
                .build();
    }

    @Override
    @Transactional
    public TransactionDepositResponseDto createDepositTransaction(TransactionDepositRequestDto request, Long recipientId) {
        User system = userRepository.findUserByUsername("system").orElseThrow(() -> new IllegalStateException("SYSTEM user not found!"));
        Wallet systemWallet = system.getWallet();
        Wallet recipientWallet = walletRepository.findByUserId(recipientId)
                .orElseThrow(RecipientNotFoundException::new);
        BigDecimal transferAmount = request.getAmount();
        TransactionType transactionType = TransactionType.DEPOSIT;
        Transaction transaction = createTransaction(systemWallet.getId(), recipientWallet.getId(), transferAmount, transactionType);
        WalletResponseDto recipientWalletDto = walletMapper.toDto(transaction.getRecipient());
        return TransactionDepositResponseDto.builder().recipient(recipientWalletDto).amount(transferAmount).build();
    }

    @Override
    public List<TransactionResponseDto> getTransactionsInPeriodAsAdmin(LocalDateTime from, LocalDateTime to, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalStateException("Username not found")
        );
        Wallet wallet = user.getWallet();
        List<Transaction> transactions = transactionRepository.findTransactionByTimestampBetween(from, to, wallet.getId());
        List<TransactionResponseDto> transactionResponseDtoList = transactions.stream().map(transactionMapper::toDto).toList();
        if (from.isAfter(to))
            throw new IllegalArgumentException("FROM must be before TO");
        return transactionResponseDtoList;
    }

    @Override
    public List<TransactionResponseDto> getTransactionsInPeriod(LocalDateTime from, LocalDateTime to, String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalStateException("Username not found")
        );
        Wallet wallet = user.getWallet();
        List<Transaction> transactions = transactionRepository.findTransactionByTimestampBetween(from, to, wallet.getId());
        System.out.println("Transactions in period " + from + " " + to + " " + transactions + " " + username);
        List<TransactionResponseDto> transactionResponseDtoList = transactions.stream().map(transactionMapper::toDto).toList();
        if (from.isAfter(to))
            throw new IllegalArgumentException("FROM must be before TO");
        return transactionResponseDtoList;
    }
}
