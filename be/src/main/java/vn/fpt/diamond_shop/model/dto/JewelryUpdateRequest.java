package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class JewelryUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Long diamondId;
    private Long typeId;
    private String imageUrl;
    private Integer settingPrice;
    private Integer laborCost;
}