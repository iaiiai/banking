package uz.iaiiai.banking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.iaiiai.banking.dto.request.UserAuthDto;
import uz.iaiiai.banking.dto.response.UserResponseDto;
import uz.iaiiai.banking.model.entity.User;

@Mapper(componentModel = "spring", uses = WalletMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "wallet", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserAuthDto dto);

    @Mapping(target = "role", source = "role.name")
    UserResponseDto toDto(User entity);
}
