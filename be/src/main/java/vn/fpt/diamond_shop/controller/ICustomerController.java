package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import vn.fpt.diamond_shop.model.dto.CartRequest;
import vn.fpt.diamond_shop.model.dto.CommonResponse;

public interface ICustomerController {
    ResponseEntity<CommonResponse> addToCart(CartRequest cr);
    ResponseEntity<CommonResponse> removeFromCart(CartRequest cr);
    ResponseEntity<CommonResponse> removeAllFromCart(CartRequest cr);
    ResponseEntity<CommonResponse> getCart(CartRequest cr);
}
