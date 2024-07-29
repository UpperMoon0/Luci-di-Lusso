package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Delivery;
import vn.fpt.diamond_shop.model.entity.OrderItem;
import vn.fpt.diamond_shop.service.IOrderItemService;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveriesResponse extends CommonResponse {
    private List<DeliveryDTO> deliveries = new ArrayList<>();

    public DeliveriesResponse(List<Delivery> deliveries, IOrderItemService orderItemService) {
        for (Delivery delivery : deliveries) {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(delivery.getOrder());
            DeliveryDTO unassignedDeliveryDTO = new DeliveryDTO(delivery, orderItems);
            this.deliveries.add(unassignedDeliveryDTO);
        }
    }
}

record DeliveryDTO(Delivery delivery, List<OrderItem> orderItems) {
}
