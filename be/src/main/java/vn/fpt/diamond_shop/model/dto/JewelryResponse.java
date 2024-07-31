package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.service.IJewelryService;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JewelryResponse extends CommonResponse {
    private final Jewelry jewelry;
    private final List<JewelrySize> sizes;
}
