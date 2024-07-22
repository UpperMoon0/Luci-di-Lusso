package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    void createOrderItemsByCartItems(List<CartItem> cartItems, Order order, int discount);
    List<OrderItem> getOrderItemsByOrder(Order order);
    List<Integer> getSaleStatistics();
    List<Integer> getJewelriesSaleStatistics();
    OrderItem getOrderItemById(Long orderItemId);
}
