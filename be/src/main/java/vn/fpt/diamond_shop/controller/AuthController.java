package vn.fpt.diamond_shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.fpt.diamond_shop.constant.EUserRole;
import vn.fpt.diamond_shop.exception.BadRequestException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.LoginResponse;
import vn.fpt.diamond_shop.model.dto.RegisterRequest;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.model.dto.LoginRequest;
import vn.fpt.diamond_shop.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.security.JwtTokenProvider;
import vn.fpt.diamond_shop.service.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * This class is responsible for handling authentication requests.
 */
@RequestMapping("/auth")
@RestController
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    /**
     * Register a new user.
     *
     * @param signUpRequest the registration request
     * @return a ResponseEntity with the registration response
     * @throws BadRequestException if the email is already in use
     */
    @PostMapping("/register")
    public ResponseEntity<CommonResponse> register(@Valid @RequestBody RegisterRequest signUpRequest) throws BadRequestException {
        CommonResponse cr = new CommonResponse();

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            cr = new CommonResponse();
            cr.setMessage("Email is already in use");
            return ResponseEntity.badRequest().body(cr);
        } else if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            cr = new CommonResponse();
            cr.setMessage("Username is already in use");
            return ResponseEntity.badRequest().body(cr);
        } else {
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setPhone(signUpRequest.getPhone());
            user.setAddress(signUpRequest.getAddress());
            user.setFullName(signUpRequest.getFullName());
            user.setDob(signUpRequest.getDob());
            user.setProvider(signUpRequest.getProvider());
            user.setRole(EUserRole.CUSTOMER);
            user.setCreateAt(LocalDateTime.now());

            userRepository.save(user);
        }
        cr.setMessage("Register successfully");
        return ResponseEntity.ok(cr);
    }

    /**
     * Log in a user.
     *
     * @param loginRequest the login request
     * @return a ResponseEntity with the login response
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPrincipal(),
                        loginRequest.getCredentials()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setAccessToken(jwt);
        loginResponse.setRole(userService.getUserByToken(jwt).getRole().name());
        loginResponse.setMessage("Login successfully");

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<CommonResponse> ping(@RequestBody String jwt) {
        CommonResponse cr = new CommonResponse();
        if (tokenProvider.validateToken(jwt)) {
            cr.setMessage("Valid token");
        } else {
            cr.setMessage("Invalid token");
        }
        return ResponseEntity.ok(cr);
    }
}