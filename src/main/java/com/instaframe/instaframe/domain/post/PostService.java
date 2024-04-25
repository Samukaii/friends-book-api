package com.instaframe.instaframe.domain.post;

import com.instaframe.instaframe.dtos.general.FileDTO;
import com.instaframe.instaframe.domain.general.exceptions.ImageNotFoundException;
import com.instaframe.instaframe.dtos.post.CreatePostDTO;
import com.instaframe.instaframe.dtos.post.PostResponseDTO;
import com.instaframe.instaframe.dtos.post.UpdatePostDTO;
import com.instaframe.instaframe.domain.post.exceptions.PostNotFoundException;
import com.instaframe.instaframe.domain.post.exceptions.UserCanNotUpdatePostException;
import com.instaframe.instaframe.services.ImageStoreService;
import com.instaframe.instaframe.domain.user.User;
import com.instaframe.instaframe.domain.user.UserRepository;
import com.instaframe.instaframe.domain.user.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ImageStoreService imageStoreService;
    private final UsersService usersService;


    public PostResponseDTO create(CreatePostDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = (User) userRepository.findByEmail(email);

        Post post = new Post(data);

        post.setCreatedBy(user);
        String imageUrl = imageStoreService.storeImage(data.image());
        post.setImageUrl(imageUrl);

        postRepository.save(post);

        return getOne(post.getId());
    }

    public void delete(Integer id) {
        Post post = postRepository.findByActiveTrueAndId(id).orElseThrow(PostNotFoundException::new);

        validateCurrentUser(post);

        post.setActive(false);

        postRepository.save(post);
    }

    public List<PostResponseDTO> getAll() {
        List<Post> posts = this.postRepository.findAllByActiveTrue();

        return posts.stream().map(this::postToPostDTO).toList();
    }

    public List<PostResponseDTO> getAllByUserId(Integer userId) {
        List<Post> posts = this.postRepository.findAllByActiveTrueAndCreatedById(userId);

        return posts.stream().map(this::postToPostDTO).toList();
    }

    public PostResponseDTO getOne(Integer id) {
        Post post = this.postRepository.findByActiveTrueAndId(id).orElseThrow(PostNotFoundException::new);

        return postToPostDTO(post);
    }

    public PostResponseDTO update(Integer id, UpdatePostDTO data) {
        Post post = this.postRepository.findByActiveTrueAndId(id).orElseThrow(PostNotFoundException::new);

        validateCurrentUser(post);

        if (data.title() != null) post.setTitle(data.title());
        if (data.description() != null) post.setDescription(data.description());

        if (data.image() != null) {
            String imageUrl = imageStoreService.storeImage(data.image());

            post.setImageUrl(imageUrl);
        }

        postRepository.save(post);

        return getOne(id);
    }

    private PostResponseDTO postToPostDTO(Post post) {
        FileDTO image = imageStoreService.getFile(post.getImageUrl()).orElseThrow(ImageNotFoundException::new);

        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getActive(),
                image,
                usersService.userToUserDTO(post.getCreatedBy()),
                post.getCreatedAt()
        );
    }

    private void validateCurrentUser(Post post) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!Objects.equals(post.getCreatedBy().getEmail(), currentUserName))
            throw new UserCanNotUpdatePostException();
    }
}
