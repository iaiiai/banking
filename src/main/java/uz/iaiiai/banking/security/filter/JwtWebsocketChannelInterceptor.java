package uz.iaiiai.banking.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import uz.iaiiai.banking.security.CustomUserDetails;
import uz.iaiiai.banking.security.CustomUserDetailsService;
import uz.iaiiai.banking.security.authentication.JwtService;

@Component
@RequiredArgsConstructor
public class JwtWebsocketChannelInterceptor implements ChannelInterceptor {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        System.out.println("STOMP command: " + accessor.getCommand());
        System.out.println("JWT header: " + accessor.getFirstNativeHeader("Authorization"));
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtService.extractUsername(token));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                accessor.setUser(authentication);
            }
        }
        return message;
    }
}
