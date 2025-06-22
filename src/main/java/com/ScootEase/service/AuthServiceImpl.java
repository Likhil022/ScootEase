package com.ScootEase.ScootEase.service;

import com.ScootEase.DTO.AuthResponse;
import com.ScootEase.DTO.LoginRequest;
import com.ScootEase.DTO.RegisterRequest;
import com.ScootEase.model.User;
import com.ScootEase.repository.UserRepository;
import com.ScootEase.ScootEase.security.JwtUtil;
import com.ScootEase.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        // Create and save new user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .Role(User.role.CUSTOMER)
                .build();

        userRepository.save(user);

        // Generate JWT token
        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Fetch user from DB
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate token
        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
