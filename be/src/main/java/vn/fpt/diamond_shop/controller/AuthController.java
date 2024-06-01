package vn.fpt.diamond_shop.controller;

import vn.fpt.diamond_shop.dto.CommonResponse;
import vn.fpt.diamond_shop.dto.LoginResponse;
import vn.fpt.diamond_shop.security.exception.BadRequestException;
import vn.fpt.diamond_shop.security.EAuthProvider;
import vn.fpt.diamond_shop.entity.User;
import vn.fpt.diamond_shop.dto.LoginRequest;
import vn.fpt.diamond_shop.dto.RegisterRequest;
import vn.fpt.diamond_shop.repository.UserRepository;
import vn.fpt.diamond_shop.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class is responsible for handling authentication requests.
 */
@RestController
public class AuthController implements IAuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final TokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        //this.tokenProvider = tokenProvider;
    }

    /**
     * Register a new user.
     *
     * @param signUpRequest the registration request
     * @return a ResponseEntity with the registration response
     * @throws BadRequestException if the email is already in use
     */
    @Override
    public ResponseEntity<CommonResponse> register(@Valid @RequestBody RegisterRequest signUpRequest) throws BadRequestException {
        // TODO: Implement this method
        return null;
    }

    /**
     * Log in a user.
     *
     * @param loginRequest the login request
     * @return a ResponseEntity with the login response
     */
    @Override
    public ResponseEntity<LoginResponse> login(@Valid LoginRequest loginRequest) {
        // TODO: Not now
        return null;
    }
}