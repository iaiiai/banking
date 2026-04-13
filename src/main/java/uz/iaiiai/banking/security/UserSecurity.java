package uz.iaiiai.banking.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {
    public boolean isOwner(String username, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return ((UserDetails) auth.getPrincipal()).getUsername().equals(username);
    }
}
