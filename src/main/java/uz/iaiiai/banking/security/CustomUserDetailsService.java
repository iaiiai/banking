package uz.iaiiai.banking.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uz.iaiiai.banking.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.iaiiai.banking.repository.UserRepository;

import java.util.List;


@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User with username "
                                        + username +
                                        "  not found"
                        )
                );
        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(user.getRole().getName())
                )
        );
    }
}
