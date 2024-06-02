package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.Cart;
import vn.fpt.diamond_shop.model.dto.CartRequest;
import vn.fpt.diamond_shop.model.dto.CommonResponse;

@RequestMapping("/customer")
@RestController
public class CustomerController implements ICustomerController {
    Cart shoppingCart;

    @Autowired
    public CustomerController(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    @PostMapping("/cart/add")
    public ResponseEntity<CommonResponse> addToCart(CartRequest cr) {
        return null;
    }

    @Override
    @DeleteMapping("/cart/remove")
    public ResponseEntity<CommonResponse> removeFromCart(CartRequest cr) {
        return null;
    }

    @Override
    @DeleteMapping("/cart/removeAll")
    public ResponseEntity<CommonResponse> removeAllFromCart(CartRequest cr) {
        return null;
    }

    @Override
    @GetMapping("/cart/get")
    public ResponseEntity<CommonResponse> getCart(CartRequest cr) {
        return null;
    }
}
