package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class CreateChargeRequest {
    private String stripeToken;
    private long voucherId;
}
