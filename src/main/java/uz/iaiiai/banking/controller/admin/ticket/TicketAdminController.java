package uz.iaiiai.banking.controller.admin.ticket;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.iaiiai.banking.dto.response.TicketResponseDto;
import uz.iaiiai.banking.security.CustomUserDetails;
import uz.iaiiai.banking.service.TicketService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
@PreAuthorize("hasRole('ADMIN')")
public class TicketAdminController {
    private final TicketService ticketService;

    @GetMapping
    List<TicketResponseDto> getTicketsAsAdmin(
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ) {
        return ticketService.getTicketsAsAdmin(page, size);
    }

    @PatchMapping("/{ticketId}")
    public TicketResponseDto closeTicketAsAdmin(
            @PathVariable Long ticketId,
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        return ticketService.closeTicketAsAdmin(ticketId);
    }

    @GetMapping("/{ticketId}")
    public TicketResponseDto getTicketAsAdmin(
            @PathVariable Long ticketId,
            @AuthenticationPrincipal CustomUserDetails auth
    ) {
        return ticketService.getTicketAsAdmin(ticketId);
    }

}
