package uz.iaiiai.banking.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.iaiiai.banking.dto.request.UserAuthDto;
import uz.iaiiai.banking.dto.response.UserLoginResponseDto;
import uz.iaiiai.banking.dto.response.UserRegisterResponseDto;
import uz.iaiiai.banking.exception.InvalidLoginCredentialsException;
import uz.iaiiai.banking.exception.UserAlreadyExistsException;
import uz.iaiiai.banking.exception.UserNotFoundException;
import uz.iaiiai.banking.mapper.UserMapper;
import uz.iaiiai.banking.model.entity.Role;
import uz.iaiiai.banking.model.entity.User;
import uz.iaiiai.banking.model.entity.Wallet;
import uz.iaiiai.banking.model.enumeration.WalletType;
import uz.iaiiai.banking.repository.RoleRepository;
import uz.iaiiai.banking.repository.UserRepository;
import uz.iaiiai.banking.security.authentication.JwtService;
import uz.iaiiai.banking.service.AuthService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Transactional
    public UserRegisterResponseDto register(UserAuthDto auth) {
        String username = auth.getUsername();
        String password = passwordEncoder.encode(auth.getPassword());
        boolean userExists = userRepository.existsByUsername(username);
        if (userExists)
            throw new UserAlreadyExistsException();
        Role role = roleRepository.findRoleByName("USER")
                .orElseThrow(
                        () -> new IllegalStateException("Default user role USER not found")
                );
        User user = userMapper.toEntity(auth);
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setWalletType(WalletType.USER);
        user.setWallet(wallet);
        user.setPassword(password);
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return new UserRegisterResponseDto(savedUser.getId());
    }

    @Transactional
    public UserLoginResponseDto login(UserAuthDto auth) {
        String username = auth.getUsername();
        String password = auth.getPassword();
        User user = userRepository.findUserByUsername(auth.getUsername())
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidLoginCredentialsException();
        String token = jwtService.generateToken(user);
        return new UserLoginResponseDto(token);
    }
}
