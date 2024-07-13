package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;

public interface IJewelryTypeService {
    JewelryType getFirst();
    List<JewelryType> getAll();
}
