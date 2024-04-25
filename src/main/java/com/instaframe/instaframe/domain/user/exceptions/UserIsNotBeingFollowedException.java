package com.instaframe.instaframe.domain.user.exceptions;

public class UserIsNotBeingFollowedException extends RuntimeException {
    public UserIsNotBeingFollowedException() {
        super("User is not followed");
    }
}
