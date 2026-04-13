package uz.iaiiai.banking.controller.admin.user;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.iaiiai.banking.dto.response.UserResponseDto;
import uz.iaiiai.banking.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserAdminController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getUsersAsAdmin(
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size
    ) {
        return userService.getUsersAsAdmin(page, size);
    }

}
