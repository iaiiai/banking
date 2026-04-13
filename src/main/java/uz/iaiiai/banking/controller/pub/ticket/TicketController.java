package uz.iaiiai.banking.controller.ticket;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.iaiiai.banking.dto.request.TicketCreateRequestDto;
import uz.iaiiai.banking.dto.response.TicketResponseDto;
import uz.iaiiai.banking.security.CustomUserDetails;
import uz.iaiiai.banking.service.TicketService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/ticket")
    public TicketResponseDto createTicket(
            @RequestBody @Valid TicketCreateRequestDto dto,
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        Long userId = auth.getId();
        return ticketService.createTicket(dto, userId);
    }

    @GetMapping("/ticket/{ticketId}")
    public TicketResponseDto getTicket(
            @PathVariable Long ticketId,
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        Long userId = auth.getId();
        return ticketService.getTicket(ticketId, userId);
    }

    @GetMapping("/ticket")
    public List<TicketResponseDto> getAllTickets(
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        Long userId = auth.getId();
        return ticketService.getAllTicketsByUser(userId);
    }
}
