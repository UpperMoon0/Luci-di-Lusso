package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;
import vn.fpt.diamond_shop.repository.IOrderItemRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {
    private final IOrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(IOrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void createOrderItemsByCartItems(List<CartItem> cartItems, Order order) {
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setJewelry(cartItem.getJewelry());
            orderItem.setJewelrySize(cartItem.getJewelrySize());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getJewelry().getPrice());
            orderItem.setOrder(order);
            order.setCreateAt(LocalDateTime.now());
            orderItemRepository.save(orderItem);
        }
    }
}
