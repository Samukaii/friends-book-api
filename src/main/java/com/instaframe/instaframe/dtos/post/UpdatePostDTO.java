package com.instaframe.instaframe.dtos.post;

import org.springframework.web.multipart.MultipartFile;

public record UpdatePostDTO(
        String title,
        String description,
        MultipartFile image
) { }
