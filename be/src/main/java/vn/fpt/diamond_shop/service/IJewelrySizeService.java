package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;

public interface IJewelrySizeService {
    List<JewelrySize> getJewelrySizesByJewelryType(JewelryType type);
    JewelrySize getDefaultSizeByJewelryType(JewelryType type);
    JewelrySize findFirst();
}
