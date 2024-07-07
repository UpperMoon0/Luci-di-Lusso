package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.model.entity.Order;

import java.util.List;

public interface IOrderItemService {
    void createOrderItemsByCartItems(List<CartItem> cartItems, Order order);
}
