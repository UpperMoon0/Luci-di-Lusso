package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class AddToCartRequest {
    @NotNull
    private Long jewelryId;
    @NotNull
    private Integer quantity;
    @NotNull
    private Long sizeId;
}
