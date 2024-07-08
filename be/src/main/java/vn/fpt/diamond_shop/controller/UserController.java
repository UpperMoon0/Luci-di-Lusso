package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.CustomerProfileResponse;
import vn.fpt.diamond_shop.model.dto.UpdateCustomerProfileRequest;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.service.UserDetailsServiceImpl;

import java.time.LocalDate;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/get-customer-profile")
    public ResponseEntity<CustomerProfileResponse> getCustomerProfile (@RequestHeader("Authorization") String authorizationHeader) {
        try {
            User user = userDetailsService.getUserByToken(authorizationHeader);
            CustomerProfileResponse response = new CustomerProfileResponse(user);
            response.setMessage("Customer profile retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            CustomerProfileResponse response = new CustomerProfileResponse(new User());
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/update-customer-profile")
    public ResponseEntity<CommonResponse> updateCustomerProfile (@RequestHeader("Authorization") String authorizationHeader,
                                                                 @RequestBody UpdateCustomerProfileRequest body) {
        try {
            String fullName = body.getFullName();
            String address = body.getAddress();
            String phone = body.getPhone();
            String imageUrl = body.getImageUrl();
            LocalDate dob = body.getDob();
            userDetailsService.updateCustomerProfile(authorizationHeader, fullName, address, phone, imageUrl, dob);
            CommonResponse response = new CommonResponse();
            response.setMessage("Customer profile updated successfully");
            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
