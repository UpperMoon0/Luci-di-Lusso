package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class SaveDiamondRequest {
    private long id;
    private long clarityId;
    private long cutId;
    private long colorId;
    private float carat;
    private long shapeId;
    private int quantity;
}
