package com.instaframe.instaframe.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByActiveTrue();
    List<Post> findAllByActiveTrueAndCreatedById(Integer createdById);
    Optional<Post> findByActiveTrueAndId(Integer id);
}
