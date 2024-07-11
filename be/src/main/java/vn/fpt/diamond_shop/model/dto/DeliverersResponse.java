package vn.fpt.diamond_shop.model.dto;

import vn.fpt.diamond_shop.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DeliverersResponse extends CommonResponse {
    private List<DelivererDTO> deliverers = new ArrayList<>();

    public DeliverersResponse(List<User> deliverers) {
        for (User deliverer : deliverers) {
            this.deliverers.add(new DelivererDTO(deliverer.getId(),
                    deliverer.getFullName()));
        }
    }
}

record DelivererDTO(Long id, String fullName) {}
