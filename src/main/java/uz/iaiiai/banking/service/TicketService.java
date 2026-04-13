package uz.iaiiai.banking.service;

import uz.iaiiai.banking.dto.request.TicketCreateRequestDto;
import uz.iaiiai.banking.dto.request.TicketMessageSendRequestDto;
import uz.iaiiai.banking.dto.response.TicketMessageResponseDto;
import uz.iaiiai.banking.dto.response.TicketResponseDto;

import java.util.List;

public interface TicketService {
    TicketResponseDto createTicket(TicketCreateRequestDto dto, Long userId);
    TicketMessageResponseDto sendTicketMessage(TicketMessageSendRequestDto dto, Long ticketId, Long senderId);
    TicketResponseDto getTicket(Long ticketId, Long userId);
    TicketResponseDto getTicketAsAdmin(Long ticketId);
    TicketResponseDto closeTicket(Long ticketId, Long userId);
    TicketResponseDto closeTicketAsAdmin(Long ticketId);
    List<TicketResponseDto> getTickets(Long userId);
    List<TicketResponseDto> getTicketsAsAdmin(int page, int size);
}
