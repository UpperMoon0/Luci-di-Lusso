package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;
import vn.fpt.diamond_shop.repository.IOrderItemRepository;

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
    private final IJewelryService jewelryService;

    @Override
    public void createOrderItemsByCartItems(List<CartItem> cartItems, Order order) {
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setJewelry(cartItem.getJewelry());
            orderItem.setJewelrySize(cartItem.getJewelrySize());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(jewelryService.calculateJewelryPriceWithSize(cartItem.getJewelry(), cartItem.getJewelrySize()));
            orderItem.setOrder(order);
            orderItem.setCreateAt(LocalDateTime.now());
            orderItemRepository.save(orderItem);
        }
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
}
