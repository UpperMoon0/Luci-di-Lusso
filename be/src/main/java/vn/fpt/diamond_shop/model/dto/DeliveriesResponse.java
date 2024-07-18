package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Delivery;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveriesResponse extends CommonResponse {
    private List<DeliveryDTO> deliveries = new ArrayList<>();

    public DeliveriesResponse(List<Delivery> deliveries) {
        for (Delivery delivery : deliveries) {
            DeliveryDTO deliveryDTO = new DeliveryDTO(
                    delivery.getId(),
                    delivery.getOrder().getUser().getCustomer().getFullName(),
                    delivery.getOrder().getUser().getCustomer().getPhone(),
                    delivery.getOrder().getUser().getCustomer().getAddress(),
                    delivery.getOrder().getUser().getCustomer().getEmail(),
                    delivery.getCreateAt().toString()
            );

            this.deliveries.add(deliveryDTO);
        }
    }
}

record DeliveryDTO(Long id,
                   String customerName,
                   String customerPhone,
                   String customerAddress,
                   String customerEmail,
                   String createAt) {
}
