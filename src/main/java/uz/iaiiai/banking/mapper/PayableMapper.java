package uz.iaiiai.banking.mapper;

import org.mapstruct.*;
import uz.iaiiai.banking.dto.request.PayableRequestDto;
import uz.iaiiai.banking.dto.response.PayableResponseDto;
import uz.iaiiai.banking.model.entity.Payable;

@Mapper(componentModel = "spring", uses = WalletMapper.class)
public interface PayableMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "wallet", ignore = true)
    Payable toEntity(PayableRequestDto dto);

    @Mapping(target = "isActive", source = "active")
    PayableResponseDto toDto(Payable entity);

}
