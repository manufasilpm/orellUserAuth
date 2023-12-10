package com.sample.User.controller;


import com.sample.User.dto.AuthenticationRequest;
import com.sample.User.dto.AuthenticationResponse;
import com.sample.User.exception.EmailAlreadyExistsException;
import com.sample.User.model.RegisterRequest;
import com.sample.User.repository.UserRegistrationRepository;
import com.sample.User.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRegistrationRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationService service;

    @Autowired
    public AuthController(UserRegistrationRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.service = authenticationService;
    }

    @PostMapping ("/register" )
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) throws EmailAlreadyExistsException {
        return ResponseEntity.ok(service.register(request));

    }

    @PostMapping ("/authenticate" )
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));

    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


    @GetMapping("/isTokenExpired")
    public ResponseEntity<Boolean> isTokenExpired(@RequestParam String token) {

        boolean isExpired = service.expiredOrNot(token);

        return ResponseEntity.ok(isExpired);
    }


}