package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.JewelryType;
import vn.fpt.diamond_shop.repository.IJewelrySizeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JewelrySizeService implements IJewelrySizeService {
    private final IJewelrySizeRepository jewelrySizeService;

    @Autowired
    public JewelrySizeService(IJewelrySizeRepository jewelrySizeService) {
        this.jewelrySizeService = jewelrySizeService;
    }

    @Override
    public List<JewelrySize> getJewelrySizesByJewelryType(JewelryType type) {
        return jewelrySizeService.findAllByType(type);
    }

    @Override
    public JewelrySize getDefaultSizeByJewelryType(JewelryType type) {
        return jewelrySizeService.findFirstByType(type).orElse(null);
    }

    @Override
    public JewelrySize findFirst() {
        return jewelrySizeService.findFirstBy().orElse(null);
    }
}
