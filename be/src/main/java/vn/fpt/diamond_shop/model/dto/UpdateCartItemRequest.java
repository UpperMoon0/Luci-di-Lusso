package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long itemId;
    private Integer quantity;
}
