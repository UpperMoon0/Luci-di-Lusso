package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelryTag;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
public class JewelriesResponse extends CommonResponse {
    HashMap<Jewelry, List<JewelryTag>> jewelriesMap;
}
