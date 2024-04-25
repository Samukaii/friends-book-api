package com.instaframe.instaframe.domain.user.exceptions;

public class UserCannotFollowThemselvesException extends RuntimeException {
    public UserCannotFollowThemselvesException() {
        super("User cannot follow themselves");
    }
}
