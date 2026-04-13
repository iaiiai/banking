package uz.iaiiai.banking.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.iaiiai.banking.dto.request.PayableRequestDto;
import uz.iaiiai.banking.dto.request.PayableStatusUpdateRequestDto;
import uz.iaiiai.banking.dto.response.PayableResponseDto;
import uz.iaiiai.banking.dto.response.PayableStatusUpdateResponseDto;
import uz.iaiiai.banking.exception.PayableAliasTakenException;
import uz.iaiiai.banking.exception.PayableNotFoundException;
import uz.iaiiai.banking.mapper.PayableMapper;
import uz.iaiiai.banking.model.entity.Payable;
import uz.iaiiai.banking.model.entity.Wallet;
import uz.iaiiai.banking.model.enumeration.WalletType;
import uz.iaiiai.banking.repository.PayableRepository;
import uz.iaiiai.banking.service.PayableService;

import java.util.List;

@Service
@AllArgsConstructor
public class PayableServiceImpl implements PayableService {
    private final PayableMapper payableMapper;
    private final PayableRepository payableRepository;

    @Override
    public PayableResponseDto createPayableAsAdmin(PayableRequestDto dto) {
        boolean payableAliasTaken = payableRepository.existsByAlias(dto.getAlias());
        if (payableAliasTaken) throw new PayableAliasTakenException();
        Payable payable = payableMapper.toEntity(dto);
        Wallet wallet = new Wallet();
        wallet.setWalletType(WalletType.PAYABLE);
        wallet.setPayable(payable);
        payable.setWallet(wallet);
        Payable savedPayable = payableRepository.save(payable);
        return payableMapper.toDto(savedPayable);
    }

    @Override
    @Transactional
    public PayableStatusUpdateResponseDto setPayableStatusAsAdmin(String alias, PayableStatusUpdateRequestDto dto) {
        boolean active = dto.isActive();
        Payable payable = payableRepository.findPayableByAlias(alias)
                .orElseThrow(PayableNotFoundException::new);
        payable.setActive(active);
        return PayableStatusUpdateResponseDto.builder()
                .id(payable.getId())
                .alias(payable.getAlias())
                .isActive(payable.isActive())
                .build();
    }

    public PayableResponseDto getPayable(String alias) {
        Payable payable = payableRepository.findPayableByAlias(alias)
                .orElseThrow(PayableNotFoundException::new);
        return payableMapper.toDto(payable);
    }

    @Override
    public List<PayableResponseDto> getPayables(int page, int size) {
        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by("id").ascending()
        );
        return payableRepository.findPayablesByIsActiveIsTrue(pageable)
                .getContent()
                .stream()
                .map(payableMapper::toDto)
                .toList();
    }

    public List<PayableResponseDto> getPayablesAsAdmin(int page, int size) {
        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by("id").ascending()
        );
        return payableRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(payableMapper::toDto)
                .toList();
    }
}
