package com.instaframe.instaframe.controllers;

import com.instaframe.instaframe.domain.user.AuthenticationService;
import com.instaframe.instaframe.domain.user.UsersService;
import com.instaframe.instaframe.dtos.user.AuthenticationDTO;
import com.instaframe.instaframe.dtos.user.LoginResponseDTO;
import com.instaframe.instaframe.dtos.user.RegisterDTO;
import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody @Valid AuthenticationDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterDTO data, UriComponentsBuilder uriComponentsBuilder){

        var uri = uriComponentsBuilder.path("/auth/login").build().toUri();

        return ResponseEntity.created(uri).body(usersService.register(data));
    }

    @GetMapping("/validate_current_user")
    public ResponseEntity<UserResponseDTO> validateUser(){
        return ResponseEntity.ok(usersService.validateCurrentUser());
    }
}
