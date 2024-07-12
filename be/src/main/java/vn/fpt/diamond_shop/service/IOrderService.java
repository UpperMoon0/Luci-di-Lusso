package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Order;

import java.util.List;

public interface IOrderService {
    double createOrderFromJwtToken(String token);
    List<Order> getOrdersByUser(Long userId);
    Order getOrderById(Long orderId);
}
