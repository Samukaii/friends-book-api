package com.instaframe.instaframe.domain.user.exceptions;

public class PasswordsDoesNotMatchException extends RuntimeException {
    public PasswordsDoesNotMatchException() {
        super("Passwords does not match");
    }
}
