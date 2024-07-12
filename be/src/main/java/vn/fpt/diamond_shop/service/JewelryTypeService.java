package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.JewelryType;
import vn.fpt.diamond_shop.repository.IJewelryTypeRepository;

@Service
public class JewelryTypeService implements IJewelryTypeService {
    private final IJewelryTypeRepository jewelrySizeRepository;

    @Autowired
    public JewelryTypeService(IJewelryTypeRepository jewelrySizeRepository) {
        this.jewelrySizeRepository = jewelrySizeRepository;
    }

    @Override
    public JewelryType findFirst() {
        return jewelrySizeRepository.findFirstBy().orElse(null);
    }
}
