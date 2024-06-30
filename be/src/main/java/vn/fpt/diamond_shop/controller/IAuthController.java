package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.LoginRequest;
import vn.fpt.diamond_shop.model.dto.LoginResponse;
import vn.fpt.diamond_shop.model.dto.RegisterRequest;
import vn.fpt.diamond_shop.security.exception.BadRequestException;

import javax.validation.Valid;


public interface IAuthController {
    ResponseEntity<CommonResponse> register(@Valid RegisterRequest signUpRequest) throws BadRequestException;
    ResponseEntity<LoginResponse> login(@Valid LoginRequest loginRequest);
}
