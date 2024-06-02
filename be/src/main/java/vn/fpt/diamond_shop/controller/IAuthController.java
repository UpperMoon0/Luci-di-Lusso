package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fpt.diamond_shop.dto.CommonResponse;
import vn.fpt.diamond_shop.dto.LoginRequest;
import vn.fpt.diamond_shop.dto.LoginResponse;
import vn.fpt.diamond_shop.dto.RegisterRequest;
import vn.fpt.diamond_shop.security.exception.BadRequestException;

import javax.validation.Valid;

@RequestMapping("/auth")
public interface IAuthController {
    @PostMapping("/signup")
    ResponseEntity<CommonResponse> register(@Valid RegisterRequest signUpRequest) throws BadRequestException;
    @PostMapping("/signin")
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
}
