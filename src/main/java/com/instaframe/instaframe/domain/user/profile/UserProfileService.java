package com.instaframe.instaframe.domain.user.profile;

import com.instaframe.instaframe.domain.post.PostService;
import com.instaframe.instaframe.domain.user.UserRepository;
import com.instaframe.instaframe.dtos.post.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;
    private final PostService postService;

    public List<PostResponseDTO> getUserPosts(Integer userId) {
        return postService.getAllByUserId(userId);
    }
}
