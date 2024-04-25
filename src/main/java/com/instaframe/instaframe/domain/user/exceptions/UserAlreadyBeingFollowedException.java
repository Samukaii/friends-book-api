package com.instaframe.instaframe.domain.user.exceptions;

public class UserAlreadyBeingFollowedException extends RuntimeException {
    public UserAlreadyBeingFollowedException() {
        super("User is already followed");
    }
}
