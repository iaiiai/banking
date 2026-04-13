package uz.iaiiai.banking.service;

import uz.iaiiai.banking.dto.request.UserAuthDto;
import uz.iaiiai.banking.dto.response.UserLoginResponseDto;
import uz.iaiiai.banking.dto.response.UserRegisterResponseDto;

public interface AuthService {
    UserRegisterResponseDto register(UserAuthDto auth);
    UserLoginResponseDto login(UserAuthDto auth);
}
