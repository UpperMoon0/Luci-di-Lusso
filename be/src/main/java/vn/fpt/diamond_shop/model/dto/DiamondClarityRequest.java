package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class DiamondClarityRequest {
    private Long id;
    private String clarity;
    private Integer price;
}
