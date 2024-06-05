package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.model.entity.Jewelry;

import java.util.List;

@Getter
@Setter
@ToString
public class JewelriesResponse extends CommonResponse {
    List<Jewelry> jewelries;
}
