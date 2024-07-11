package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EJewelryType;
import vn.fpt.diamond_shop.model.entity.Jewelry;
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
    public Optional<Jewelry> getJewelryById(Long id) {
        return jewelryRepository.findById(id);
    }

    @Override
    public List<Jewelry> getAllJewelries() {
        return jewelryRepository.findAll();
    }

    @Override
    public List<Jewelry> getJewelriesByFilter(List<EJewelryType> types, Double minPrice, Double maxPrice) {
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
            double price = jewelry.getPrice();
            if (maxPrice == null || maxPrice == 0) {
                return price >= minPrice;
            } else {
                return price >= minPrice && price <= maxPrice;
            }
        });

        return jewelryStream.toList();
    }
}
