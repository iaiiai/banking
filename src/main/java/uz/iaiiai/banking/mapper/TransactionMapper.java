package uz.iaiiai.banking.mapper;

import org.mapstruct.Mapper;
import uz.iaiiai.banking.dto.response.TransactionResponseDto;
import uz.iaiiai.banking.model.entity.Transaction;

@Mapper(componentModel = "spring", uses = WalletMapper.class)
public interface TransactionMapper {
    TransactionResponseDto toDto(Transaction entity);
}
