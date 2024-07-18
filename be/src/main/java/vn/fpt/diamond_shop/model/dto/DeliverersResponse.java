package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Account;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeliverersResponse extends CommonResponse {
    private List<DelivererDTO> deliverers = new ArrayList<>();

    public DeliverersResponse(List<Account> deliverers) {
        for (Account deliverer : deliverers) {
            this.deliverers.add(new DelivererDTO(deliverer.getId(),
                    deliverer.getCustomer().getFullName()));
        }
    }
}

record DelivererDTO(Long id, String fullName) {}
