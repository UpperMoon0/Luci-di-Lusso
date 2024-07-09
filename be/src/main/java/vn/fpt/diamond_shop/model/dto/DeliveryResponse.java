package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Delivery;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveryResponse extends CommonResponse {

    private List<Delivery> deliveries;

}
