package vn.fpt.diamond_shop.service.manager.jewelry;

import vn.fpt.diamond_shop.model.dto.JewelryRequest;

import java.io.IOException;
import java.util.List;

public interface MJewelryService {
    JewelryRequest addJewelry(JewelryRequest jewelryRequest) throws IOException;
    List<JewelryRequest> getAllJewelries();
    List<JewelryRequest> getAllJewelriesByName(String name);
    List<JewelryRequest> getAllJewelryTypes();
    boolean deleteJewelry(Long id);
}
