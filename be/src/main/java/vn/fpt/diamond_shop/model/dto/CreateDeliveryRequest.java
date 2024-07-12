package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class CreateDeliveryRequest {

    @NotNull
    private Long deliveryID;

    @NotNull
    private Long delivererID;

}
