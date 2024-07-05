package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.AddToCartRequest;
import vn.fpt.diamond_shop.model.entity.CartItem;

import java.util.List;

public interface ICartService {
    void addToCart(String token, Long JewelryId, Integer quantity, Long sizeId);
    List<CartItem> getCartByUserId(String token);
    void updateCartItem(String token, Long cartItemId, Integer quantity);
    void deleteCartItem(String token, Long cartItemId);
    void deleteAllCartItems(String token);
}
