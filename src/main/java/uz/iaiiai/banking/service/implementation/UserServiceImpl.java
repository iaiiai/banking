package uz.iaiiai.banking.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.iaiiai.banking.dto.response.UserResponseDto;
import uz.iaiiai.banking.exception.UserNotFoundException;
import uz.iaiiai.banking.mapper.UserMapper;
import uz.iaiiai.banking.model.entity.User;
import uz.iaiiai.banking.repository.RoleRepository;
import uz.iaiiai.banking.repository.UserRepository;
import uz.iaiiai.banking.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserResponseDto getUser(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }

    public List<UserResponseDto> getUsersAsAdmin(int page, int size) {
        Pageable pageable = PageRequest.of(
                page, size,
                Sort.by("id").ascending()
        );
        return userRepository.findAll(pageable)
                .getContent()
                .stream()
                .map(user -> userMapper.toDto(user))
                .toList();
    }
}
