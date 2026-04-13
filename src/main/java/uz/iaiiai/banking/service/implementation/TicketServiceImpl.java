package uz.iaiiai.banking.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.iaiiai.banking.dto.request.TicketCreateRequestDto;
import uz.iaiiai.banking.dto.request.TicketMessageSendRequestDto;
import uz.iaiiai.banking.dto.response.TicketMessageResponseDto;
import uz.iaiiai.banking.dto.response.TicketResponseDto;
import uz.iaiiai.banking.dto.response.UserResponseDto;
import uz.iaiiai.banking.exception.TicketNotFoundException;
import uz.iaiiai.banking.mapper.TicketMapper;
import uz.iaiiai.banking.mapper.TicketMessageMapper;
import uz.iaiiai.banking.mapper.UserMapper;
import uz.iaiiai.banking.model.entity.Ticket;
import uz.iaiiai.banking.model.entity.TicketMessage;
import uz.iaiiai.banking.model.entity.User;
import uz.iaiiai.banking.model.enumeration.TicketStatus;
import uz.iaiiai.banking.repository.TicketMessageRepository;
import uz.iaiiai.banking.repository.TicketRepository;
import uz.iaiiai.banking.repository.UserRepository;
import uz.iaiiai.banking.service.TicketService;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TicketMessageRepository ticketMessageRepository;
    private final TicketMapper ticketMapper;
    private final TicketMessageMapper ticketMessageMapper;

    @Override
    @Transactional
    public TicketResponseDto createTicket(TicketCreateRequestDto dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        UserResponseDto userResponseDto = userMapper.toDto(user);
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setSubject(dto.getSubject());
        ticket.setStatus(TicketStatus.OPEN);

        Ticket savedTicket = ticketRepository.save(ticket);
        return TicketResponseDto.builder()
                .id(savedTicket.getId())
                .subject(savedTicket.getSubject())
                .user(userResponseDto)
                .status(savedTicket.getStatus())
                .build();
    }

    @Override
    public TicketResponseDto getTicket(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findTicketByIdAndUserId(ticketId, userId)
                .orElseThrow(TicketNotFoundException::new);
        UserResponseDto user = userMapper.toDto(ticket.getUser());
        List<TicketMessageResponseDto> messages = ticket.getMessages()
                .stream()
                .map(ticketMessageMapper::toDto)
                .toList();
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .subject(ticket.getSubject())
                .user(user)
                .status(ticket.getStatus())
                .messages(messages)
                .build();
    }

    @Override

    public TicketResponseDto getTicketAsAdmin(Long ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId)
                .orElseThrow(TicketNotFoundException::new);
        UserResponseDto user = userMapper.toDto(ticket.getUser());
        List<TicketMessageResponseDto> messages = ticket.getMessages()
                .stream()
                .map(ticketMessageMapper::toDto)
                .toList();
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .subject(ticket.getSubject())
                .user(user)
                .status(ticket.getStatus())
                .messages(messages)
                .build();
    }

    @Override
    public List<TicketResponseDto> getTickets(Long userId) {
        List<Ticket> ticketList = ticketRepository.findTicketsByUserId(userId);
        List<TicketResponseDto> ticketResponseDtoList = ticketList.stream()
                .map(ticketMapper::toDto)
                .toList();
        return ticketResponseDtoList;
    }

    @Override
    public List<TicketResponseDto> getTicketsAsAdmin(int page, int size) {
        List<Ticket> ticketList = ticketRepository.findAll();
        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by("id").ascending()
        );
        List<TicketResponseDto> ticketResponseDtoList = ticketList.stream()
                .map(ticketMapper::toDto)
                .toList();
        return ticketResponseDtoList;
    }

    @Override
    @Transactional
    public TicketMessageResponseDto sendTicketMessage(TicketMessageSendRequestDto dto, Long ticketId, Long senderId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketNotFoundException::new);
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        UserResponseDto senderResponseDto = userMapper.toDto(sender);
        TicketMessage ticketMessage = new TicketMessage();
        ticketMessage.setTicket(ticket);
        ticketMessage.setSender(sender);
        ticketMessage.setContent(dto.getContent());

        TicketMessage savedTicket = ticketMessageRepository.save(ticketMessage);

        return TicketMessageResponseDto.builder()
                .id(savedTicket.getId())
                .sender(senderResponseDto)
                .content(savedTicket.getContent())
                .build();
    }

    @Override
    @Transactional
    public TicketResponseDto closeTicket(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findTicketByIdAndUserId(ticketId, userId)
                .orElseThrow(TicketNotFoundException::new);
        UserResponseDto userResponseDto = userMapper.toDto(ticket.getUser());
        ticket.setStatus(TicketStatus.CLOSED);
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .subject(ticket.getSubject())
                .user(userResponseDto)
                .status(ticket.getStatus())
                .build();
    }

    @Override
    @Transactional
    public TicketResponseDto closeTicketAsAdmin(Long ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId)
                .orElseThrow(TicketNotFoundException::new);
        UserResponseDto userResponseDto = userMapper.toDto(ticket.getUser());
        ticket.setStatus(TicketStatus.CLOSED);
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .subject(ticket.getSubject())
                .user(userResponseDto)
                .status(ticket.getStatus())
                .build();
    }
}
