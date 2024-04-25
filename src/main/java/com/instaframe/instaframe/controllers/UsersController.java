package com.instaframe.instaframe.controllers;

import com.instaframe.instaframe.domain.user.UsersService;
import com.instaframe.instaframe.dtos.user.UpdateUserDTO;
import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getOneUser(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<UserResponseDTO>> getFollowers(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getFollowers(id));
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<UserResponseDTO>> getFollowing(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getFollowing(id));
    }

    @PatchMapping("/{id}/follow")
    public ResponseEntity<String> followUser(@PathVariable Integer id) {
        service.follow(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable Integer id) {
        service.unfollow(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @ModelAttribute @Valid UpdateUserDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
