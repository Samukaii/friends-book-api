package com.instaframe.instaframe.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instaframe.instaframe.domain.general.exceptions.InvalidTokenException;
import com.instaframe.instaframe.domain.user.UserRepository;
import com.instaframe.instaframe.dtos.general.ErrorResponseDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = this.recoverToken(request);

            String email = tokenService.validateToken(token);

            UserDetails user = userRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (InvalidTokenException exception) {
            //throwError(response, HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new InvalidTokenException();

        return authHeader.replace("Bearer ", "");
    }

    private void throwError(HttpServletResponse response, Integer status, String message) throws IOException {
        ErrorResponseDTO errorDTO = new ErrorResponseDTO(message);

        response.setStatus(status);
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();
        writer.print(mapper.writeValueAsString(errorDTO));
        writer.flush();
    }
}
