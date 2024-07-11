package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.DeliverersResponse;
import vn.fpt.diamond_shop.model.dto.UserProfileResponse;
import vn.fpt.diamond_shop.model.dto.UpdateUserProfileRequest;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.service.IUserService;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;

    }

    @GetMapping("/get-profile")
    public ResponseEntity<UserProfileResponse> getCustomerProfile (@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            User user = userService.getUserByToken(jwtToken);
            UserProfileResponse response = new UserProfileResponse(user);
            response.setMessage("Customer profile retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            UserProfileResponse response = new UserProfileResponse(new User());
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<CommonResponse> updateCustomerProfile (@RequestHeader("Authorization") String authorizationHeader,
                                                                 @RequestBody UpdateUserProfileRequest body) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            String fullName = body.getFullName();
            String address = body.getAddress();
            String phone = body.getPhone();
            String imageUrl = body.getImageUrl();
            LocalDate dob = body.getDob();
            userService.updateCustomerProfile(jwtToken, fullName, address, phone, imageUrl, dob);
            CommonResponse response = new CommonResponse();
            response.setMessage("Customer profile updated successfully");
            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-deliverers")
    public ResponseEntity<DeliverersResponse> getDeliverers() {
        List<User> deliverers = userService.getAllDeliverer();
        DeliverersResponse response = new DeliverersResponse(deliverers);
        response.setMessage("Deliverers retrieved successfully");
        return ResponseEntity.ok(response);
    }
}
