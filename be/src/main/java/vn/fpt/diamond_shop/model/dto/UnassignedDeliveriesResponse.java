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
public class UnassignedDeliveriesResponse extends CommonResponse {
    private List<UnassignedDeliveryDTO> unassignedDeliveries = new ArrayList<>();

    public UnassignedDeliveriesResponse(List<Delivery> deliveries, IOrderItemService orderItemService) {
        for (Delivery delivery : deliveries) {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(delivery.getOrder());
            UnassignedDeliveryDTO unassignedDeliveryDTO = new UnassignedDeliveryDTO(delivery, orderItems);
            this.unassignedDeliveries.add(unassignedDeliveryDTO);
        }
    }
}

record UnassignedDeliveryDTO(Delivery delivery, List<OrderItem> orderItems) {
}
