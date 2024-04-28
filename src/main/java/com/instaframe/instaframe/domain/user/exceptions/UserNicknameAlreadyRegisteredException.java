package com.instaframe.instaframe.domain.user.exceptions;

public class UserNicknameAlreadyRegisteredException extends RuntimeException {
    public UserNicknameAlreadyRegisteredException() {
        super("User nickname already registered");
    }
}
