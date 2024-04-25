package com.instaframe.instaframe.domain.general.exceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("Image not found");
    }
}
