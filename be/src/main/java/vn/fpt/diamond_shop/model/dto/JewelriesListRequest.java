package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constant.EJewelryType;

import java.util.List;

@Getter
@Setter
@ToString
public class JewelriesListRequest {
    List<EJewelryType> types;
    Double minPrice;
    Double maxPrice;
}
