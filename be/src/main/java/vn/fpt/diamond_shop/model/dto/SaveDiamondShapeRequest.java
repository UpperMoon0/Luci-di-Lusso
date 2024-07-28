package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class SaveDiamondShapeRequest {
    private Long id;
    private String shape;
    private Float priceMultiplier;
}
