package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveJewelryTypeRequest;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;

public interface IJewelryTypeService {
    List<JewelryType> findAll();

    void save(SaveJewelryTypeRequest request);

    void toggleStatus(Long id);
}
