package com.instaframe.instaframe.domain.user.exceptions;

public class UserEmailAlreadyRegisteredException extends RuntimeException {
    public UserEmailAlreadyRegisteredException() {
        super("User email is already registered");
    }
}
