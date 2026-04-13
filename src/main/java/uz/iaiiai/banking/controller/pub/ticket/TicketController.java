package uz.iaiiai.banking.controller.pub.ticket;

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
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public TicketResponseDto createTicket(
            @RequestBody @Valid TicketCreateRequestDto dto,
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        Long userId = auth.getId();
        return ticketService.createTicket(dto, userId);
    }

    @PatchMapping("/{ticketId}")
    public TicketResponseDto closeTicket(
            @PathVariable Long ticketId,
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        Long userId = auth.getId();
        return ticketService.closeTicket(ticketId, userId);
    }

    @GetMapping("/{ticketId}")
    public TicketResponseDto getTicket(
            @PathVariable Long ticketId,
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        Long userId = auth.getId();
        return ticketService.getTicket(ticketId, userId);
    }

    @GetMapping
    public List<TicketResponseDto> getTickets(
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        Long userId = auth.getId();
        return ticketService.getTickets(userId);
    }
}
