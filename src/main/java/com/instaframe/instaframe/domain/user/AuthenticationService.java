package com.instaframe.instaframe.domain.user;

import com.instaframe.instaframe.domain.user.exceptions.IncorrectEmailOrPasswordException;
import com.instaframe.instaframe.dtos.user.AuthenticationDTO;
import com.instaframe.instaframe.dtos.user.LoginResponseDTO;
import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import com.instaframe.instaframe.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponseDTO login(AuthenticationDTO authenticationDTO) {
        User user = validateCredentials(authenticationDTO);

        String token = tokenService.generateToken(user);
        UserResponseDTO userDTO = usersService.userToUserDTO(user);

        return new LoginResponseDTO(token, userDTO);
    }

    private User validateCredentials(AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.email(),
                authenticationDTO.password()
        );

        try {
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            return (User) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
            throw new IncorrectEmailOrPasswordException();
        }
    }
}
