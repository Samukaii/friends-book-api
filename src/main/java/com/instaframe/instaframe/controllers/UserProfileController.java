package com.instaframe.instaframe.controllers;

import com.instaframe.instaframe.domain.user.UsersService;
import com.instaframe.instaframe.domain.user.profile.UserProfileService;
import com.instaframe.instaframe.dtos.post.PostResponseDTO;
import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("user_profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService profileService;
    private final UsersService usersService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getOneUser(@PathVariable Integer id) {
        return ResponseEntity.ok(usersService.getOne(id));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostResponseDTO>> getPosts(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.getUserPosts(id));
    }
}
