package uz.iaiiai.banking.mapper;

import org.mapstruct.Mapper;
import uz.iaiiai.banking.dto.response.WalletResponseDto;
import uz.iaiiai.banking.model.entity.Wallet;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletResponseDto toDto(Wallet entity);
}
