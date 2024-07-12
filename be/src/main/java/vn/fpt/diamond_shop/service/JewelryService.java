package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EJewelryType;
import vn.fpt.diamond_shop.model.entity.Diamond;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.repository.IJewelryRepository;
import vn.fpt.diamond_shop.repository.IJewelryTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class JewelryService implements IJewelryService {
    private final IJewelryRepository jewelryRepository;
    private final IJewelryTypeRepository jewelryTypeRepository;

    @Autowired
    public JewelryService(IJewelryRepository jewelryRepository,
                          IJewelryTypeRepository jewelryTypeRepository) {
        this.jewelryRepository = jewelryRepository;
        this.jewelryTypeRepository = jewelryTypeRepository;
    }

    @Override
    public Jewelry getJewelryById(Long id) {
        return jewelryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Jewelry> getAllJewelries() {
        return jewelryRepository.findAll();
    }

    @Override
    public List<Jewelry> getJewelriesByFilter(List<EJewelryType> types, Integer minPrice, Integer maxPrice, String keyword) {
        Stream<Jewelry> jewelryStream;
        if (types.isEmpty()) {
            // Initialize stream with all jewelries if type list is empty
            jewelryStream = jewelryRepository.findAll().stream();
        } else {
            // If type list is not empty, initialize stream with jewelries that match the types
            jewelryStream = types.stream()
                    .map(jewelryTypeRepository::findByType)
                    .flatMap(optionalType -> optionalType
                            .map(jewelryType -> jewelryRepository.findAllByType(jewelryType).stream())
                            .orElseGet(Stream::empty))
                    .distinct(); // Remove duplicates based on Jewelry's equals() and hashCode() methods
        }

        // Apply price filter to the stream
        jewelryStream = jewelryStream.filter(jewelry -> {
            double price = calculateJewelryPrice(jewelry);
            if (maxPrice == null || maxPrice == 0) {
                return price >= minPrice;
            } else {
                return price >= minPrice && price <= maxPrice;
            }
        });

        // Apply keyword filter to the stream if keyword is not null or empty
        if (keyword != null && !keyword.isEmpty()) {
            final String lowerCaseKeyword = keyword.toLowerCase();
            jewelryStream = jewelryStream.filter(jewelry -> jewelry.getName().toLowerCase().contains(lowerCaseKeyword));
        }

        return jewelryStream.toList();
    }

    @Override
    public Integer calculateJewelryPrice(Jewelry jewelry) {
        Diamond diamond = jewelry.getDiamond();
        int diamondPrice = (int) ((diamond.getCut().getPrice() + diamond.getColor().getPrice() + diamond.getClarity().getPrice())
                * diamond.getCarat()
                * diamond.getShape().getPriceMultiplier());
        return diamondPrice + jewelry.getSettingPrice() + jewelry.getLaborCost();
    }

    @Override
    public Integer calculateJewelryPriceWithSize(Jewelry jewelry, JewelrySize size) {
        Diamond diamond = jewelry.getDiamond();
        int diamondPrice = (int) ((diamond.getCut().getPrice() + diamond.getColor().getPrice() + diamond.getClarity().getPrice())
                * diamond.getCarat()
                * diamond.getShape().getPriceMultiplier());
        return (Integer) (int) (diamondPrice + jewelry.getSettingPrice() * size.getPriceMultiplier() + jewelry.getLaborCost());
    }
}
