package com.instaframe.instaframe.dtos.post;

import com.instaframe.instaframe.dtos.general.FileDTO;
import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import jakarta.annotation.Nullable;

import java.util.Date;

public record PostResponseDTO(
        Integer id,
        String title,
        String description,
        Boolean active,
        @Nullable
        FileDTO image,
        UserResponseDTO createdBy,
        Date createdAt
        ) {
}
