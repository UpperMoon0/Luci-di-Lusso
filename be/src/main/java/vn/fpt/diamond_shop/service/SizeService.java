package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.JewelryType;
import vn.fpt.diamond_shop.repository.IJewelrySizeRepository;
import vn.fpt.diamond_shop.repository.IJewelryTypeRepository;

import java.util.List;

@Service
public class SizeService implements IJewelrySizeService {
    private final IJewelrySizeRepository jewelrySizeService;

    @Autowired
    public SizeService(IJewelrySizeRepository jewelrySizeService) {
        this.jewelrySizeService = jewelrySizeService;
    }

    @Override
    public List<JewelrySize> getJewelrySizesByJewelryType(JewelryType type) {
        return jewelrySizeService.findAllByType(type);
    }
}
