package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.constant.EJewelryType;
import vn.fpt.diamond_shop.model.dto.JewelryUpdateRequest;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;

import java.util.List;
import java.util.Optional;

public interface IJewelryService {
    Jewelry getJewelryById(Long id);
    List<Jewelry> getAllJewelries();
    List<Jewelry> getJewelriesByFilter(List<EJewelryType> types, Integer minPrice, Integer maxPrice);
    Integer calculateJewelryPrice(Jewelry jewelry);
    Integer calculateJewelryPriceWithSize(Jewelry jewelry, JewelrySize size);
    void deleteJewelryById(Long id);
    void updateJewelry(JewelryUpdateRequest request);
    void createNewJewelry();
}
