package uz.iaiiai.banking.security.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import uz.iaiiai.banking.dto.response.ApiErrorResponseDto;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                authException.getMessage(),
                request.getServletPath()
        );
        log.error(authException.getCause().getMessage());
        objectMapper.writeValue(response.getOutputStream(), error);
    }
}
