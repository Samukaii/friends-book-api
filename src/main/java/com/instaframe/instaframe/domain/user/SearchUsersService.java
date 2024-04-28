package com.instaframe.instaframe.domain.user;

import com.instaframe.instaframe.dtos.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUsersService {
    private final UserRepository repository;
    private final UsersService usersService;

    public List<UserResponseDTO> search(String search) {
        Integer currentUserId = usersService.getAuthenticatedUser().getId();

        List<User> users = this.repository.searchAllByText(currentUserId, search);

        return users.stream().map(usersService::userToUserDTO).toList();
    }
}
