package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.JewelryCollection;

import java.util.List;

public interface IJewelryCollectionService {
    List<JewelryCollection> getAllCollections();
    JewelryCollection getCollectionById(Long id);
}
