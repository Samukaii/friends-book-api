package com.instaframe.instaframe.config;

import com.instaframe.instaframe.domain.general.exceptions.ImageNotFoundException;
import com.instaframe.instaframe.domain.post.exceptions.PostNotFoundException;
import com.instaframe.instaframe.domain.post.exceptions.UserCanNotUpdatePostException;
import com.instaframe.instaframe.domain.user.exceptions.*;
import com.instaframe.instaframe.dtos.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleImageNotFoundException(ImageNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePostNotFound(PostNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(IncorrectEmailOrPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleIncorrectEmailOrPassword(IncorrectEmailOrPasswordException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyRegistered(UserAlreadyRegisteredException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserCanNotUpdatePostException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserCanNotUpdatePostException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(UserAlreadyBeingFollowedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserAlreadyBeingFollowedException(UserAlreadyBeingFollowedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserCannotFollowThemselvesException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserCannotFollowThemselvesException(UserCannotFollowThemselvesException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(UserIsNotBeingFollowedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserIsNotFollowedException(UserIsNotBeingFollowedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }
}
