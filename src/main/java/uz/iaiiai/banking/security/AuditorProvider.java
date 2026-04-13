package uz.iaiiai.banking.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorProvider implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return Optional.empty();
        }
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            return Optional.of(userDetails.getId());
        }
        return Optional.empty();
    }
}
