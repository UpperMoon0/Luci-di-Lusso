package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Order;

import java.util.List;

public interface IOrderService {
    int createOrderFromJwtToken(String token, int discount);
    List<Order> getOrdersByCustomer(Long userId);
    Order getOrderById(Long orderId);
}
