package com.instaframe.instaframe.dtos.user;

import org.springframework.web.multipart.MultipartFile;

public record UpdateUserDTO(
        String name,
        String surname,
        String email,
        MultipartFile profile,
        MultipartFile cover
) {
}
