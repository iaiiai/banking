package uz.iaiiai.banking.security.authorization;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import tools.jackson.databind.ObjectMapper;
import uz.iaiiai.banking.dto.response.ApiErrorResponseDto;

import java.io.IOException;
import java.time.LocalDateTime;

public class RestAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ApiErrorResponseDto error = new ApiErrorResponseDto(
                LocalDateTime.now().toString(),
                accessDeniedException.getMessage(),
                request.getServletPath()
        );
        objectMapper.writeValue(response.getOutputStream(), error);

    }
}
