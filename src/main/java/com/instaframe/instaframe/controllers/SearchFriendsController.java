package com.instaframe.instaframe.controllers;

import com.instaframe.instaframe.domain.user.SearchUsersService;
import com.instaframe.instaframe.domain.user.UsersService;
import com.instaframe.instaframe.dtos.user.UpdateUserDTO;
import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("search_friends")
@RequiredArgsConstructor
public class SearchFriendsController {
    private final SearchUsersService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getOneUser(@RequestParam(required = false) Optional<String> search) {
        return ResponseEntity.ok(service.search(search.orElse("")));
    }
}
