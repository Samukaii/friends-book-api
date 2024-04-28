package com.instaframe.instaframe.controllers;

import com.instaframe.instaframe.domain.post.*;
import com.instaframe.instaframe.dtos.post.CreatePostDTO;
import com.instaframe.instaframe.dtos.post.PostResponseDTO;
import com.instaframe.instaframe.dtos.post.UpdatePostDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostsController {
    private final PostService service;

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        return ResponseEntity.ok(service.getAllByCurrentUserFollowing());
    }

    @GetMapping("/by_user/{id}")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getAllByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getOnePost(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@ModelAttribute @Valid CreatePostDTO body) {
        PostResponseDTO newPost = service.create(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PostResponseDTO> updateProduct(@PathVariable Integer id, @ModelAttribute @Valid UpdatePostDTO data) throws IOException {
        PostResponseDTO newPost = service.update(id, data);

        return ResponseEntity.ok(newPost);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Post> deleteProduct(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
