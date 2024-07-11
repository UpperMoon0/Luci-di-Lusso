package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import vn.fpt.diamond_shop.model.entity.Diamond;

@Data
public class DiamondUpdateRequest {
    private Long id;
    private String clarity;
    private String cut;
    private String color;
    private Float carat;
    private String shape;
}
