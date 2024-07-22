package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.CartItem;

import java.util.List;

public interface ICartService {
    void addToCart(String token, Long JewelryId, Integer quantity, Long sizeId) throws RuntimeException;
    List<CartItem> getCartByUserId(String token) throws RuntimeException;
    void updateCartItem(String token, Long cartItemId, Integer quantity) throws RuntimeException;
    void deleteCartItem(String token, Long cartItemId) throws RuntimeException;
    void deleteAllCartItems(String token) throws RuntimeException;
}
