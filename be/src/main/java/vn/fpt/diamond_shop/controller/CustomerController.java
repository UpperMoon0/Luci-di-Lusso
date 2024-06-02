package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.Cart;
import vn.fpt.diamond_shop.model.dto.CartRequest;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.GetCartResponse;

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
    public ResponseEntity<GetCartResponse> getCart(CartRequest cr) {
        // Copy the content of the Cart to the 'cart' hashmap in the GetCartResponse object before returning
        return null;
    }
}
