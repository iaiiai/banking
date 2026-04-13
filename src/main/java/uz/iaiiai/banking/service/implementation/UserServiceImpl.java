package uz.iaiiai.banking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.iaiiai.banking.dto.request.CreateUserDto;
import uz.iaiiai.banking.dto.response.UserResponseDto;
import uz.iaiiai.banking.exception.UserAlreadyExistsException;
import uz.iaiiai.banking.exception.UserNotFoundException;
import uz.iaiiai.banking.mapper.UserMapper;
import uz.iaiiai.banking.model.entity.Role;
import uz.iaiiai.banking.model.entity.User;
import uz.iaiiai.banking.repository.RoleRepository;
import uz.iaiiai.banking.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Transactional
    public UserResponseDto create(CreateUserDto dto) {
        boolean userExists = userRepository.existsByUsername(dto.getUsername());
        if (userExists){
            throw new UserAlreadyExistsException();
        }
        Role role = roleRepository.findRoleByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default user role USER not found"));
        User user = userMapper.toEntity(dto);
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserResponseDto get(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }

    public List<UserResponseDto> getAll(int page, int size) {
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
