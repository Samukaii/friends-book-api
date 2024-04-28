package com.instaframe.instaframe.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String email,
        @NotBlank
        String nickname,
        @NotBlank
        String password,
        @NotBlank
        String passwordConfirmation
) {
}
