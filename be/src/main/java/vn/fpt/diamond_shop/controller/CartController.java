package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.AddToCartRequest;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.GetCartResponse;
import vn.fpt.diamond_shop.model.dto.UpdateCartItemRequest;
import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.service.ICartService;
import vn.fpt.diamond_shop.service.IJewelryService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final ICartService cartService;
    private final IJewelryService jewelryService;

    @Autowired
    public CartController(ICartService cartService,
                          IJewelryService jewelryService) {
        this.cartService = cartService;
        this.jewelryService = jewelryService;
    }

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> add(@RequestHeader("Authorization") String authorizationHeader,
                                              @Valid @RequestBody AddToCartRequest request) {
        CommonResponse cr = new CommonResponse();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            Long jewelryId = request.getJewelryId();
            Integer quantity = request.getQuantity();
            Long sizeId = request.getSizeId();

            try {
                cartService.addToCart(token, jewelryId, quantity, sizeId);
                cr.setMessage("Added to cart successfully");
                return ResponseEntity.ok(cr);
            } catch (RuntimeException e) {
                cr.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cr);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GetCartResponse(new ArrayList<>(), jewelryService));
        }
    }

    @GetMapping("/get-cart")
    public ResponseEntity<GetCartResponse> getCart(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                List<CartItem> cartItems = cartService.getCartByUserId(token);
                GetCartResponse response = new GetCartResponse(cartItems, jewelryService);
                response.setMessage("Cart retrieved successfully");
                return ResponseEntity.ok(response);
            } catch (RuntimeException e) {
                GetCartResponse response = new GetCartResponse(new ArrayList<>(), jewelryService);
                response.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GetCartResponse(new ArrayList<>(), jewelryService));
        }
    }

    @PutMapping("/update-item")
    public ResponseEntity<CommonResponse> updateItem(@RequestHeader("Authorization") String authorizationHeader,
                                                     @RequestBody UpdateCartItemRequest request) {
        CommonResponse cr = new CommonResponse();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                cartService.updateCartItem(token, request.getItemId(), request.getQuantity());
                cr.setMessage("Updated item successfully");
                return ResponseEntity.ok(cr);
            } catch (RuntimeException e) {
                cr.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cr);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(cr);
        }
    }

    @DeleteMapping("/delete-item")
    public ResponseEntity<CommonResponse> deleteItem(@RequestHeader("Authorization") String authorizationHeader,
                                                     @RequestParam Long itemId) {
        CommonResponse cr = new CommonResponse();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                cartService.deleteCartItem(token, itemId);
                cr.setMessage("Deleted item successfully");
                return ResponseEntity.ok(cr);
            } catch (RuntimeException e) {
                cr.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cr);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(cr);
        }
    }

    @DeleteMapping("/delete-cart")
    public ResponseEntity<CommonResponse> deleteCart(@RequestHeader("Authorization") String authorizationHeader) {
        CommonResponse cr = new CommonResponse();
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                cartService.deleteAllCartItems(token);
                cr.setMessage("Deleted all items successfully");
                return ResponseEntity.ok(cr);
            } catch (RuntimeException e) {
                cr.setMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cr);
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(cr);
        }
    }
}
