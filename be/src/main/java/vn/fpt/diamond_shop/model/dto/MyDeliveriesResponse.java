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
public class MyDeliveriesResponse extends CommonResponse {
    private List<MyDeliveryDTO> deliveries = new ArrayList<>();

    public MyDeliveriesResponse(List<Delivery> deliveries, IOrderItemService orderItemService) {
        for (Delivery delivery : deliveries) {
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(delivery.getOrder());
            MyDeliveryDTO deliveryDTO = new MyDeliveryDTO(delivery, orderItems);

            this.deliveries.add(deliveryDTO);
        }
    }
}

record MyDeliveryDTO(Delivery delivery,
                     List<OrderItem> orderItems) {
}
