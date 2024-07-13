package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.JewelryType;
import vn.fpt.diamond_shop.repository.IJewelryTypeRepository;

import java.util.List;

@Service
public class JewelryTypeService implements IJewelryTypeService {
    private final IJewelryTypeRepository jewelryTypeRepository;

    @Autowired
    public JewelryTypeService(IJewelryTypeRepository jewelrySizeRepository) {
        this.jewelryTypeRepository = jewelrySizeRepository;
    }

    @Override
    public JewelryType getFirst() {
        return jewelryTypeRepository.findFirstBy().orElse(null);
    }

    @Override
    public List<JewelryType> getAll() {
        return jewelryTypeRepository.findAll();
    }
}
