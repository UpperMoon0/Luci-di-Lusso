package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.SaveJewelrySizeRequest;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.JewelryType;
import vn.fpt.diamond_shop.repository.IJewelrySizeRepository;
import vn.fpt.diamond_shop.repository.IJewelryTypeRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JewelrySizeService implements IJewelrySizeService {
    private final IJewelrySizeRepository jewelrySizeRepository;
    private final IJewelryTypeRepository jewelryTypeRepository;

    @Override
    public List<JewelrySize> getByJewelryType(JewelryType type) {
        return jewelrySizeRepository.findAllByType(type);
    }

    @Override
    public void save(SaveJewelrySizeRequest request) {
        JewelrySize jewelrySize;
        if (request.getId() == 0) {
            jewelrySize = new JewelrySize();
        } else {
            jewelrySize = jewelrySizeRepository.findById(request.getId()).orElse(null);
            if (jewelrySize == null) {
                throw new IllegalArgumentException("Jewelry Size not found");
            }
        }

        JewelryType type = jewelryTypeRepository.findById(request.getTypeId()).orElse(null);
        if (type == null) {
            throw new IllegalArgumentException("Invalid jewelry size update request");
        }

        jewelrySize.setType(type);
        jewelrySize.setSize(request.getSize());
        jewelrySize.setUnit(request.getUnit());
        jewelrySize.setPriceMultiplier(request.getPriceMultiplier());
        jewelrySizeRepository.save(jewelrySize);
    }

    @Override
    public void toggleStatus(Long id) {
        JewelrySize jewelrySize = jewelrySizeRepository.findById(id).orElse(null);
        if (jewelrySize == null) {
            throw new IllegalArgumentException("Jewelry Size not found");
        }

        if ("ACTIVE".equals(jewelrySize.getStatus())) {
            jewelrySize.setStatus("INACTIVE");
        } else {
            jewelrySize.setStatus("ACTIVE");
        }

        jewelrySizeRepository.save(jewelrySize);
    }

    @Override
    public List<JewelrySize> findAll() {
        return jewelrySizeRepository.findAll();
    }
}