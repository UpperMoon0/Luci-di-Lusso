package vn.fpt.diamond_shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.LoginResponse;
import vn.fpt.diamond_shop.security.exception.BadRequestException;
import vn.fpt.diamond_shop.constant.EAuthProvider;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.model.dto.LoginRequest;
import vn.fpt.diamond_shop.model.dto.RegisterRequest;
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
@RequestMapping("/auth")
@RestController
public class AuthController implements IAuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Register a new user.
     *
     * @param signUpRequest the registration request
     * @return a ResponseEntity with the registration response
     * @throws BadRequestException if the email is already in use
     */
    @Override
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> register(@Valid @RequestBody RegisterRequest signUpRequest) throws BadRequestException {
        // TODO: Implement this method
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email has already existed!");
        } else {
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setFullName(signUpRequest.getFullName());
            user.setProvider(EAuthProvider.valueOf(signUpRequest.getProvider()));
            userRepository.save(user);
        }
        CommonResponse cr = new CommonResponse();
        cr.setMessage("Register successfully");
        return ResponseEntity.ok(cr);
    }

    /**
     * Log in a user.
     *
     * @param loginRequest the login request
     * @return a ResponseEntity with the login response
     */
    @Override
    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPrincipal(),
                        loginRequest.getCredential()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(jwt);
        loginResponse.setMessage("Login successfully");

        return ResponseEntity.ok(loginResponse);
    }
}