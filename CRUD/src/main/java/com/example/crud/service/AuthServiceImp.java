package com.example.crud.service;

import com.example.crud.config.jwtService;
import com.example.crud.controller.models.AuthResponse;
import com.example.crud.controller.models.AuthenticationRequest;
import com.example.crud.controller.models.RegisterRequest;
import com.example.crud.entity.Role;
import com.example.crud.entity.person;
import com.example.crud.repository.PersonRepository;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtService JwtService;
    private final AuthenticationManager authenticationManager;
    private final com.example.crud.config.jwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        var Person = person.builder().name(request.getName()).surname(request.getSurname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        personRepository.save(Person);
        var jwtToken = JwtService.generateToken(Person);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {                                                           
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var person = personRepository.findPersonByEmail(request.getEmail()).orElseThrow();
        var jwtToken= jwtService.generateToken(person);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
