package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

@Data
public class SetJewelrySizeRequest {
    long jewelryId;
    long sizeId;
}
