package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class ConfirmOrder {
    private Long orderId;
    boolean isConfirm;
}
