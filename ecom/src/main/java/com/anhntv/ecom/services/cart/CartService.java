package com.anhntv.ecom.services.cart;

import com.anhntv.ecom.dto.AddProductInCartDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO dto);
}
