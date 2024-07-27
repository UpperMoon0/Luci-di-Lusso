package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class DiamondCutRequest {
    private Long id;
    private String cut;
    private Integer price;
}
