package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveJewelrySizeRequest;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;

public interface IJewelrySizeService {
    List<JewelrySize> getByJewelryType(JewelryType type);

    void save(SaveJewelrySizeRequest request);

    void toggleStatus(Long id);

    List<JewelrySize> findAll();
}
