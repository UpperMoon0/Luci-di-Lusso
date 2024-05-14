package vn.fpt.diamond_shop.security.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import vn.fpt.diamond_shop.security.exception.ResourceNotFoundException;
import vn.fpt.diamond_shop.security.model.User;
import vn.fpt.diamond_shop.repository.UserRepository;
import vn.fpt.diamond_shop.security.CurrentUser;
import vn.fpt.diamond_shop.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
