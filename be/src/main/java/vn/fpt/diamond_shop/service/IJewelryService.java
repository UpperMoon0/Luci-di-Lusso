package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.constant.EJewelryType;
import vn.fpt.diamond_shop.model.entity.Jewelry;

import java.util.List;
import java.util.Optional;

public interface IJewelryService {
    Optional<Jewelry> getJewelryById(Long id);
    List<Jewelry> getAllJewelries();
    List<Jewelry> getJewelriesByFilter(List<EJewelryType> types, Double minPrice, Double maxPrice);
}