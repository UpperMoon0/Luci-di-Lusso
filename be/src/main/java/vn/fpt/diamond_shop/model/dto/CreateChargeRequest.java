package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class CreateChargeRequest {
    private String token;
    private Integer amount;
    private String customerName;
}
