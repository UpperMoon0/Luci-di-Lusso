package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import vn.fpt.diamond_shop.exception.BadRequestException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.LoginResponse;
import vn.fpt.diamond_shop.model.dto.RegisterRequest;
import vn.fpt.diamond_shop.model.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.security.JwtTokenProvider;
import vn.fpt.diamond_shop.service.AccountService;

import javax.validation.Valid;

/**
 * This class is responsible for handling authentication requests.
 */
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AccountService accountService;
    private final JwtTokenProvider tokenProvider;

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
        accountService.register(signUpRequest);
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
        String jwt = accountService.login(loginRequest);
        LoginResponse loginResponse = new LoginResponse();
        Account account = accountService.getUserByToken(jwt).orElse(null);
        if (account == null) {
            loginResponse.setMessage("Invalid token");
            return ResponseEntity.badRequest().body(loginResponse);
        }
        loginResponse.setAccessToken(jwt);
        loginResponse.setRole(account.getRole().name());
        loginResponse.setMessage("Login successfully");

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<CommonResponse> ping(@RequestBody String jwt) {
        CommonResponse cr = new CommonResponse();
        if (tokenProvider.validateToken(jwt)) {
            Account user = accountService.getUserByToken(jwt).orElse(null);

            if (user == null) {
                cr.setMessage("Invalid token");
                return ResponseEntity.badRequest().body(cr);
            }

            String role = user.getRole().name();
            cr.setMessage(role);
        } else {
            cr.setMessage("Invalid token");
        }
        return ResponseEntity.ok(cr);
    }
}