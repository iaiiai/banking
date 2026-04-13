package uz.iaiiai.banking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.iaiiai.banking.dto.response.TicketResponseDto;
import uz.iaiiai.banking.model.entity.Ticket;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TicketMapper {
    TicketResponseDto toDto(Ticket ticket);
}
