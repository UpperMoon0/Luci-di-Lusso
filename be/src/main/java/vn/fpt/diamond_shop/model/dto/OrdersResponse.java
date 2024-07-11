package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;
import vn.fpt.diamond_shop.service.IOrderItemService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrdersResponse extends CommonResponse {
    private List<OrderDTO> orders = new ArrayList<>();

    public OrdersResponse(List<Order> orders, IOrderItemService orderItemService) {
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);
            if (orderItems.isEmpty()) {
                continue;
            }
            OrderItem firstItem = orderItems.get(0);
            String firstProductName = firstItem.getJewelry().getName();
            JewelrySize size = firstItem.getJewelrySize();
            String firstProductSize = size.getSize() + " " + size.getUnit();
            String firstProductQuantity = firstItem.getQuantity().toString();
            Double price = orderItems.stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();
            String timeAgo = formatTimeAgo(order.getCreateAt());

            OrderDTO orderDTO = new OrderDTO(
                order.getId(),
                firstProductName,
                firstProductSize,
                firstProductQuantity,
                price,
                timeAgo
            );

            this.orders.add(orderDTO);
        }
    }

    private String formatTimeAgo(LocalDateTime orderCreateTime) {
        long secondsAgo = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - orderCreateTime.toEpochSecond(ZoneOffset.UTC);
        if (secondsAgo >= 86400) { // More than or equal to 1 day
            return (secondsAgo / 86400) + " days ago";
        } else if (secondsAgo >= 3600) { // More than or equal to 1 hour
            return (secondsAgo / 3600) + " hours ago";
        } else {
            return (secondsAgo / 60) + " minutes ago"; // Less than 1 hour
        }
    }
}

record OrderDTO(Long id,
                String firstProductName,
                String firstProductSize,
                String firstProductQuantity,
                Double price,
                String timeAgo) {}