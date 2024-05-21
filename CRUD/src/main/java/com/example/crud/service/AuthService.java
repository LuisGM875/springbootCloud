package com.example.crud.service;

import com.example.crud.controller.models.AuthResponse;
import com.example.crud.controller.models.AuthenticationRequest;
import com.example.crud.controller.models.RegisterRequest;

public interface AuthService {

    public AuthResponse register (RegisterRequest request);
    public AuthResponse authenticate (AuthenticationRequest request);

}
