package vn.fpt.diamond_shop.service.manager.jewelry;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EJewelryType;
import vn.fpt.diamond_shop.model.dto.JewelryRequest;
import vn.fpt.diamond_shop.model.entity.Diamond;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelryType;
import vn.fpt.diamond_shop.repository.IDiamondRepository;
import vn.fpt.diamond_shop.repository.IJewelryRepository;
import vn.fpt.diamond_shop.repository.IJewelryTypeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MJewelryServiceImpl implements MJewelryService{
    private final IJewelryRepository jewelryRepository;
    private final IJewelryTypeRepository jewelryTypeRepository;
    private final IDiamondRepository diamondRepository;

    public JewelryRequest addJewelry(JewelryRequest jewelryRequest){
        Jewelry jewelry = new Jewelry();
        jewelry.setName(jewelryRequest.getName());
        jewelry.setDescription(jewelryRequest.getDescription());
        jewelry.setImageUrl(jewelryRequest.getImageUrl());
        jewelry.setPrice(jewelryRequest.getPrice());
        JewelryType jewelryType = jewelryTypeRepository.findByType(jewelryRequest.getJewelryType())
                .orElseThrow(() -> new RuntimeException("JewelryType not found"));
        jewelry.setType(jewelryType);

        Diamond diamond = diamondRepository.findById(jewelryRequest.getDiamondId())
                .orElseThrow(() -> new RuntimeException("Diamond not found"));
        jewelry.setDiamond(diamond);
        return jewelryRepository.save(jewelry).getJewelryRequest();
    }

    public List<JewelryRequest> getAllJewelries() {
        List<Jewelry> jewelries = jewelryRepository.findAll();
        return jewelries.stream().map(Jewelry::getJewelryRequest).collect(Collectors.toList());
    }

    public List<JewelryRequest> getAllJewelriesByName(String name) {
        List<Jewelry> jewelries = jewelryRepository.findAllByNameContaining(name);
        return jewelries.stream().map(Jewelry::getJewelryRequest).collect(Collectors.toList());
    }

    public List<JewelryRequest> getAllJewelryTypes() {
        return jewelryTypeRepository.findAll().stream()
                .map(type -> {
                    JewelryRequest request = new JewelryRequest();
                    request.setJewelryType(EJewelryType.valueOf(type.getType().name()));
                    return request;
                })
                .collect(Collectors.toList());
    }

    public boolean deleteJewelry(Long id) {
        Optional<Jewelry> optionalJewelry = jewelryRepository.findById(id);
        if(optionalJewelry.isPresent()) {
            jewelryRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
