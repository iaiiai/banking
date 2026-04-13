package uz.iaiiai.banking.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.iaiiai.banking.dto.response.UserResponseDto;
import uz.iaiiai.banking.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getAll(
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ) {
        return userService.getAll(page, size);
    }

    @GetMapping("/{username}")
    @PreAuthorize("@userSecurity.isOwner(#username, authentication) or hasRole('ADMIN')")
    public UserResponseDto get(
            @PathVariable
            String
                    username
    ) {
        return userService.get(username);
    }

}
