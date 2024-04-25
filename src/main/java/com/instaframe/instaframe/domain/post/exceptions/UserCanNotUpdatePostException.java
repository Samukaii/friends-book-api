package com.instaframe.instaframe.domain.post.exceptions;

public class UserCanNotUpdatePostException extends RuntimeException {
    public UserCanNotUpdatePostException() {
        super("User can't update post");
    }
}
