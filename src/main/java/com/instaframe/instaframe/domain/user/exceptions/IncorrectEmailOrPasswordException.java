package com.instaframe.instaframe.domain.user.exceptions;

public class IncorrectEmailOrPasswordException extends RuntimeException {
    public IncorrectEmailOrPasswordException() {
        super("Incorrect email or password");
    }
}
