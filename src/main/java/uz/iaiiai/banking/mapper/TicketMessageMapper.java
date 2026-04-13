package uz.iaiiai.banking.mapper;

import org.mapstruct.Mapper;
import uz.iaiiai.banking.dto.response.TicketMessageResponseDto;
import uz.iaiiai.banking.model.entity.TicketMessage;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TicketMessageMapper {
    TicketMessageResponseDto toDto(TicketMessage entity);
}
