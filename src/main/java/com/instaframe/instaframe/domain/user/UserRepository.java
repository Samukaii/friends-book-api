package com.instaframe.instaframe.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String email);
    UserDetails findByNickname(String nickname);
    UserDetails findByNicknameOrEmail(String nickname, String email);
    @Query(value = "select u from users u where u.active = true and u.id <> ?1 and " +
            "(lower(concat(u.name, ' ', u.surname, ' ', u.nickname)) like %?2%) order by u.name asc")
    List<User> searchAllByText(Integer currentUserId, String search);
}
