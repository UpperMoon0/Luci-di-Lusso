package vn.fpt.diamond_shop.controller;

import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.UserSelfUpdateRequest;
import vn.fpt.diamond_shop.security.exception.ResourceNotFoundException;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.repository.UserRepository;
import vn.fpt.diamond_shop.security.CurrentUser;
import vn.fpt.diamond_shop.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import vn.fpt.diamond_shop.model.dto.UserUpdateRequest;

import javax.validation.Valid;

@RequestMapping("/shop/user")
@RestController
public class UserController implements IUserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PutMapping("/me/update")
    @PreAuthorize("hasRole('USER')")
    public User updateCurrentUser(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody UserSelfUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        if (userUpdateRequest.getName() != null) {
            user.setUsername(userUpdateRequest.getName());
        }
        if (userUpdateRequest.getEmail() != null) {
            user.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getImageUrl() != null) {
            user.setImageUrl(userUpdateRequest.getImageUrl());
        }

        return userRepository.save(user);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        Long userId = userUpdateRequest.getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (userUpdateRequest.getName() != null) {
            user.setUsername(userUpdateRequest.getName());
        }
        if (userUpdateRequest.getEmail() != null) {
            user.setEmail(userUpdateRequest.getEmail());
        }
        if (userUpdateRequest.getImageUrl() != null) {
            user.setImageUrl(userUpdateRequest.getImageUrl());
        }

        return userRepository.save(user);
    }
}
