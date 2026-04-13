package uz.iaiiai.banking.service;

import uz.iaiiai.banking.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto getUser(String username);
    List<UserResponseDto> getUsersAsAdmin(int page, int size);
}
