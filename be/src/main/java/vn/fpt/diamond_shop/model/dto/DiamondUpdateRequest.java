package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import vn.fpt.diamond_shop.model.entity.Diamond;

@Data
public class DiamondUpdateRequest {
    private Long id;
    private Long clarityId;
    private Long cutId;
    private Long colorId;
    private Float carat;
    private Long shapeId;
}
