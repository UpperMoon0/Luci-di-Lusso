package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.AddToCartRequest;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.GetCartResponse;
import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.service.ICartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final ICartService cartService;

    @Autowired
    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> add(@RequestHeader("Authorization") String authorizationHeader,
                                              @RequestBody AddToCartRequest request) {
        CommonResponse cr = new CommonResponse();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                cartService.addToCart(token, request);
                cr.setMessage("Added to cart successfully");
                return ResponseEntity.ok(cr);
            } catch (RuntimeException e) {
                cr.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cr);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GetCartResponse(new ArrayList<>()));
        }
    }

    @GetMapping("/get-cart")
    public ResponseEntity<GetCartResponse> getCart(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // Use the updated getCartByUserId method
            List<CartItem> cartItems = cartService.getCartByUserId(token);
            GetCartResponse response = new GetCartResponse(cartItems);
            response.setMessage("Cart retrieved successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GetCartResponse(new ArrayList<>()));
        }
    }
}
