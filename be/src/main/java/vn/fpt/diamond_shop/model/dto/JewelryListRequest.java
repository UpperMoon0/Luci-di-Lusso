package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class JewelryListRequest {
    List<String> types;
    Integer minPrice;
    Integer maxPrice;
    String keyword;
}