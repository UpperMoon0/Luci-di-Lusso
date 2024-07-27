package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class DiamondColorRequest {
    private Long id;
    private String color;
    private Integer price;
}
