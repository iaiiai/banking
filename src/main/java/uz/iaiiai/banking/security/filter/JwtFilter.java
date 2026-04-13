package uz.iaiiai.banking.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.iaiiai.banking.security.authentication.JwtService;
import uz.iaiiai.banking.security.authentication.RestAuthenticationEntryPoint;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {

            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                if (!jwtService.isTokenExpired(token)) {
                    String username = jwtService.extractUsername(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            restAuthenticationEntryPoint.commence(
                    request,
                    response,
                    new BadCredentialsException("There is problem with your JWT token", ex)
            );
        }
    }
}
