package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.AddToCartRequest;
import vn.fpt.diamond_shop.model.entity.CartItem;

import java.util.List;

public interface ICartService {
    void addToCart(String token, Long JewelryId, Integer quantity, Long sizeId);
    List<CartItem> getCartByUserId(String token);
}
