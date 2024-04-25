package com.instaframe.instaframe.domain.user;

import com.instaframe.instaframe.domain.user.exceptions.*;
import com.instaframe.instaframe.dtos.user.RegisterDTO;
import com.instaframe.instaframe.dtos.user.UpdateUserDTO;
import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import com.instaframe.instaframe.services.ImageStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository repository;
    private final ImageStoreService storeService;

    public UserResponseDTO register(RegisterDTO request) {
        validateUserEmailAlreadyRegistered(request.email());

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        User newUser = new User(request, encryptedPassword);

        this.repository.save(newUser);

        return userToUserDTO(newUser);
    }

    public UserResponseDTO update(Integer id, UpdateUserDTO dto) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);

        if(dto.name() != null) user.setName(dto.name());
        if(dto.surname() != null) user.setSurname(dto.surname());
        if(dto.email() != null) user.setEmail(dto.email());

        if (dto.profile() != null) {
            String imageUrl = storeService.storeImage(dto.profile());

            user.setProfileUrl(imageUrl);
        }

        if (dto.cover() != null) {
            String imageUrl = storeService.storeImage(dto.cover());

            user.setCoverUrl(imageUrl);
        }

        repository.save(user);

        return userToUserDTO(user);
    }

    public List<UserResponseDTO> getAll() {
        List<User> users = repository.findAll();

        return users.stream().map(this::userToUserDTO).toList();
    }

    public UserResponseDTO getOne(Integer id) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);

        return userToUserDTO(user);
    }

    public UserResponseDTO validateCurrentUser() {
        return userToUserDTO(this.getAuthenticatedUser());
    }

    public UserResponseDTO userToUserDTO(User user) {
        var profile = storeService.getFile(user.getProfileUrl());
        var cover = storeService.getFile(user.getCoverUrl());

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                isFollowing(user),
                profile,
                cover
        );
    }

    public void follow(Integer userId) {
        User user = getAuthenticatedUser();

        User userToFollow = repository.findById(userId).orElseThrow(UserNotFoundException::new);

        if(this.isFollowing(userToFollow))
            throw new UserAlreadyBeingFollowedException();

        if(Objects.equals(user.getId(), userToFollow.getId()))
            throw new UserCannotFollowThemselvesException();

        user.getFollowing().add(userToFollow);

        repository.save(user);
    }

    public void unfollow(Integer userId) {
        User user = getAuthenticatedUser();

        User userToUnfollow = repository.findById(userId).orElseThrow(UserNotFoundException::new);

        if(!user.getFollowing().contains(userToUnfollow))
            throw new UserIsNotBeingFollowedException();

        user.getFollowing().remove(userToUnfollow);

        repository.save(user);
    }

    public List<UserResponseDTO> getFollowers(Integer userId) {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<User> followers = user.getFollowers();

        return followers.stream().map(this::userToUserDTO).toList();
    }

    public List<UserResponseDTO> getFollowing(Integer userId) {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<User> followers = user.getFollowing();

        return followers.stream().map(this::userToUserDTO).toList();
    }

    User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return (User) repository.findByEmail(email);
    }

    private boolean isFollowing(User user) {
        User currentUser = this.getAuthenticatedUser();

        if(currentUser == null) return false;

        return currentUser.getFollowing().contains(user);
    }

    private void validateUserEmailAlreadyRegistered(String email) {
        if(repository.findByEmail(email) != null)
            throw new UserAlreadyRegisteredException();
    }
}
