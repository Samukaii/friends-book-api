package com.instaframe.instaframe.domain.user.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException() {
        super("User is already registered");
    }
}
