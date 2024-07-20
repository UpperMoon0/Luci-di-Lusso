package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.DeliverersResponse;
import vn.fpt.diamond_shop.model.dto.UserProfileResponse;
import vn.fpt.diamond_shop.model.dto.UpdateCustomerProfileRequest;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.service.IAccountService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    private final IAccountService userService;

    @Autowired
    public UserController(IAccountService userService) {
        this.userService = userService;

    }

    @GetMapping("/get-profile")
    public ResponseEntity<UserProfileResponse> getCustomerProfile (@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            Account user = userService.findAccountByToken(jwtToken).orElse(null);

            if (user == null) {
                UserProfileResponse response = new UserProfileResponse(new Account());
                response.setMessage("User not found");
                return ResponseEntity.badRequest().body(response);
            }

            UserProfileResponse response = new UserProfileResponse(user);
            response.setMessage("Customer profile retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            UserProfileResponse response = new UserProfileResponse(new Account());
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<CommonResponse> updateCustomerProfile (@RequestHeader("Authorization") String authorizationHeader,
                                                                 @Valid @RequestBody UpdateCustomerProfileRequest body) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            userService.updateCustomerProfile(jwtToken, body);
            CommonResponse response = new CommonResponse();
            response.setMessage("Customer profile updated successfully");
            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-deliverers")
    public ResponseEntity<DeliverersResponse> getDeliverers() {
        List<Account> deliverers = userService.findAllDeliverers();
        DeliverersResponse response = new DeliverersResponse(deliverers);
        response.setMessage("Deliverers retrieved successfully");
        return ResponseEntity.ok(response);
    }
}
