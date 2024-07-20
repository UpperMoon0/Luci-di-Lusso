package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Delivery;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnassignedDeliveriesResponse extends CommonResponse {
    private List<UnassignedDeliveryDTO> deliveries = new ArrayList<>();

    public UnassignedDeliveriesResponse(List<Delivery> deliveries) {
        for (Delivery delivery : deliveries) {
            this.deliveries.add(new UnassignedDeliveryDTO(delivery.getId(),
                    delivery.getOrder().getCustomer().getFullName(),
                    delivery.getCreateAt().toString()));
        }
    }
}

record UnassignedDeliveryDTO(Long id, String customerName, String creatAt) {}
