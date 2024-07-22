package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.JewelryCollection;
import vn.fpt.diamond_shop.repository.IJewelryCollectionRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JewelryCollectionService implements IJewelryCollectionService {
    private final IJewelryCollectionRepository jewelryCollectionRepository;

    @Override
    public List<JewelryCollection> getAllCollections() {
        return jewelryCollectionRepository.findAll();
    }
}
