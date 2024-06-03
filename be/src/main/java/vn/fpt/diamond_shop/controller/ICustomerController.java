package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import vn.fpt.diamond_shop.model.dto.CartRequest;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.GetCartResponse;

public interface ICustomerController {
    ResponseEntity<CommonResponse> addToCart(CartRequest cr);
    ResponseEntity<CommonResponse> removeFromCart(CartRequest cr);
    ResponseEntity<CommonResponse> removeAllFromCart(CartRequest cr);
    ResponseEntity<GetCartResponse> getCart(CartRequest cr);
}
