package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddToCartRequest {
    private Long productId;
    private Integer quantity;
}
