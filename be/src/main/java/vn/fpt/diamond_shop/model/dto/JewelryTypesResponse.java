package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JewelryTypesResponse extends CommonResponse {
    List<JewelryType> types;
}
