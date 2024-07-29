package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class SaveJewelrySizeRequest {
    private Long id;
    private Float size;
    private Float priceMultiplier;
    private Long typeId;
    private String unit;
}
