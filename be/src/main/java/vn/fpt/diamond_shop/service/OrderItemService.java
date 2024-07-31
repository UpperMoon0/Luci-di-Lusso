package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.IDiamondRepository;
import vn.fpt.diamond_shop.repository.IOrderItemRepository;
import vn.fpt.diamond_shop.repository.IOrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OrderItemService implements IOrderItemService {
    private final IOrderItemRepository orderItemRepository;
    private final IDiamondRepository diamondRepository;
    private final IJewelryService jewelryService;
    private final IOrderRepository orderRepository;

    @Override
    public Order createOrderItemsByCartItems(List<CartItem> cartItems, Customer customer, int discount) {
        if (cartItems.isEmpty()) {
            return null;
        }

        Order order = new Order();
        order.setCustomer(customer);
        orderRepository.save(order);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setJewelry(cartItem.getJewelry());
            orderItem.setJewelrySize(cartItem.getJewelrySize());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(jewelryService.calculateJewelryPriceWithSize(cartItem.getJewelry(), cartItem.getJewelrySize(), discount));
            orderItem.setOrder(order);
            orderItem.setCreateAt(LocalDateTime.now());
            orderItemRepository.save(orderItem);

            Diamond diamond = cartItem.getJewelry().getDiamond();
            diamond.setQuantity(diamond.getQuantity() - cartItem.getQuantity());
            diamondRepository.save(diamond);
        }

        return order;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrder(Order order) {
        return orderItemRepository.findAllByOrder(order);
    }

    @Override
    public List<Integer> getSaleStatistics() {
        List<OrderItem> orderItems = orderItemRepository.findAllByCreateAtBetween(LocalDateTime.now().minusDays(30), LocalDateTime.now());
        Map<LocalDate, Integer> dailySales = new HashMap<>();

        for (OrderItem orderItem : orderItems) {
            LocalDate date = orderItem.getCreateAt().toLocalDate();
            int saleAmount = orderItem.getPrice() * orderItem.getQuantity();
            dailySales.merge(date, saleAmount, Integer::sum);
        }

        List<Integer> saleStatistics = new ArrayList<>();
        LocalDate start = LocalDate.now().minusDays(29); // Start from 30 days ago
        for (int i = 0; i < 30; i++) {
            saleStatistics.add(dailySales.getOrDefault(start.plusDays(i), 0));
        }

        return saleStatistics;
    }

    @Override
    public List<Integer> getJewelriesSaleStatistics() {
        List<OrderItem> orderItems = orderItemRepository.findAllByCreateAtBetween(LocalDateTime.now().minusDays(30), LocalDateTime.now());
        Map<LocalDate, Integer> dailyQuantities = new HashMap<>();

        for (OrderItem orderItem : orderItems) {
            LocalDate date = orderItem.getCreateAt().toLocalDate();
            int quantity = orderItem.getQuantity();
            dailyQuantities.merge(date, quantity, Integer::sum);
        }

        List<Integer> saleStatistics = new ArrayList<>();
        LocalDate start = LocalDate.now().minusDays(29); // Start from 30 days ago
        for (int i = 0; i < 30; i++) {
            saleStatistics.add(dailyQuantities.getOrDefault(start.plusDays(i), 0));
        }

        return saleStatistics;
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }
}
