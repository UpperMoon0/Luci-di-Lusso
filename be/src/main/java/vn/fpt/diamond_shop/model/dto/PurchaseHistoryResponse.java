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
public class PurchaseHistoryResponse extends CommonResponse {
    private List<OrderDTO> orders = new ArrayList<>();

    public PurchaseHistoryResponse(List<Order> orders, IOrderItemService orderItemService) {
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);
            OrderDTO orderDTO = new OrderDTO(order, orderItems);

            this.orders.add(orderDTO);
        }
    }
}

record OrderDTO(Order order,
                List<OrderItem> orderItems) {}