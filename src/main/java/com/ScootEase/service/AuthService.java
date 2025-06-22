package com.ScootEase.service;

import com.ScootEase.DTO.AuthResponse;
import com.ScootEase.DTO.LoginRequest;
import com.ScootEase.DTO.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
