package uz.iaiiai.banking.controller.ticket;

import lombok.AllArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import uz.iaiiai.banking.dto.request.TicketMessageSendRequestDto;
import uz.iaiiai.banking.dto.response.TicketMessageResponseDto;
import uz.iaiiai.banking.security.CustomUserDetails;
import uz.iaiiai.banking.service.TicketService;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class TicketRealtimeChatController {
    private final TicketService ticketService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send.{ticketId}")
    public void sendMessage(
            @DestinationVariable Long ticketId,
            @Payload TicketMessageSendRequestDto dto,
            Principal principal,
            Message<?> msg
    ) {
        CustomUserDetails auth = (CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Long senderId = auth.getId();
        System.out.println("Message from " + auth.getUsername());
        System.out.println("Headers >>" + msg.getHeaders());
        TicketMessageResponseDto message = ticketService.sendMessage(dto, ticketId, senderId);
        messagingTemplate.convertAndSend(
                "/topic/ticket/" + ticketId,
                message
        );
    }
}
