package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveJewelryRequest;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;

import java.util.List;

public interface IJewelryService {
    Jewelry getJewelryById(Long id);
    List<Jewelry> getAllJewelries();

    List<Jewelry> getJewelriesByFilter(List<String> types, Integer minPrice, Integer maxPrice, String keyword);

    Integer calculateJewelryPrice(Jewelry jewelry);
    Integer calculateJewelryPriceWithSize(Jewelry jewelry, JewelrySize size, int discount);
    void toggleStatus(Long id);
    void save(SaveJewelryRequest request);
    boolean isJewelryInStock(Jewelry jewelry);
}
