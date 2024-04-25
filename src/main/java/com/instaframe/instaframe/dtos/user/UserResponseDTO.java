package com.instaframe.instaframe.dtos.user;

import com.instaframe.instaframe.dtos.general.FileDTO;
import jakarta.annotation.Nullable;

import java.util.Optional;

public record UserResponseDTO (
        Integer id,
        String name,
        String surname,
        String email,
        Boolean following,
        @Nullable
        Optional<FileDTO> profile,
        @Nullable
        Optional<FileDTO> cover
) {}
