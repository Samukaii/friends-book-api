package com.instaframe.instaframe.domain.post;

import com.instaframe.instaframe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByActiveTrueAndCreatedById(Integer createdById);
    Optional<Post> findByActiveTrueAndId(Integer id);
    @Query("SELECT p from posts p where p.active = true and p.createdBy in ?1 order by p.createdAt desc")
    List<Post> findPostsByUsers(List<User> users);
}
