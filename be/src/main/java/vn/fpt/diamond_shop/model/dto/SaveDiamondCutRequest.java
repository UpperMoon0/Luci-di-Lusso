package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class SaveDiamondCutRequest {
    private Long id;
    private String cut;
    private Integer price;
}
