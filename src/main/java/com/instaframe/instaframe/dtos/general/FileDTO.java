package com.instaframe.instaframe.dtos.general;

public record FileDTO(
        String name,
        long size,
        String type,
        String url
) { }
