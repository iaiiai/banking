package uz.iaiiai.banking.controller.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.iaiiai.banking.dto.request.UserAuthDto;
import uz.iaiiai.banking.dto.response.UserLoginResponseDto;
import uz.iaiiai.banking.dto.response.UserRegisterResponseDto;
import uz.iaiiai.banking.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponseDto handleLogin(
            @Valid
            @RequestBody
            UserAuthDto
            dto
    ) {
        return authService.login(dto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponseDto handleRegister(
           @Valid
           @RequestBody
           UserAuthDto
           dto
    ) {
        return authService.register(dto);
    }
}
