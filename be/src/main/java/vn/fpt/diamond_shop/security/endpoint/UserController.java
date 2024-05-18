package vn.fpt.diamond_shop.security.endpoint;

import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.security.exception.ResourceNotFoundException;
import vn.fpt.diamond_shop.security.model.User;
import vn.fpt.diamond_shop.repository.UserRepository;
import vn.fpt.diamond_shop.security.CurrentUser;
import vn.fpt.diamond_shop.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import vn.fpt.diamond_shop.payload.UserUpdateRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/shop/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PutMapping("/me/update")
    @PreAuthorize("hasRole('USER')")
    public User updateCurrentUser(@CurrentUser UserPrincipal userPrincipal, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        if (userUpdateRequest.getName() != null) {
            user.setName(userUpdateRequest.getName());
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
