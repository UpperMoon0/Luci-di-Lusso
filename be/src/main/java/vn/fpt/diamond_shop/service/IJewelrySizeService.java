package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;
import java.util.Optional;

public interface IJewelrySizeService {
    List<JewelrySize> getJewelrySizesByJewelryType(JewelryType type);
    Optional<JewelrySize> getDefaultSizeByJewelryType(JewelryType type);
}
