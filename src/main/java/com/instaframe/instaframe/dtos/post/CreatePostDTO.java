package com.instaframe.instaframe.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CreatePostDTO(
        @NotBlank
        String title,
        String description,
        @NotNull
        MultipartFile image
) { }
