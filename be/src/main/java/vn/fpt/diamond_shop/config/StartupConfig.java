package vn.fpt.diamond_shop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.fpt.diamond_shop.constant.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.*;

import java.time.LocalDateTime;

@Configuration
public class StartupConfig {
    @Bean
    public CommandLineRunner initTables(IDiamondCutRepository cutRepository,
                                        IDiamondPolishRepository polishRepository,
                                        IDiamondShapeRepository shapeRepository,
                                        IDiamondClarityRepository clarityRepository,
                                        IJewelryTypeRepository jewelryTagRepository) {
        return args -> {
            for (EDiamondCut cutValue : EDiamondCut.values()) {
                DiamondCut cut = cutRepository.findByCut(cutValue);
                if (cut == null) {
                    cut = new DiamondCut();
                    cut.setCut(cutValue);
                    cut.setCreateAt(LocalDateTime.now());
                    cutRepository.save(cut);
                }
            }

            for (EDiamondPolish polishValue : EDiamondPolish.values()) {
                DiamondPolish polish = polishRepository.findByPolish(polishValue);
                if (polish == null) {
                    polish = new DiamondPolish();
                    polish.setPolish(polishValue);
                    polish.setCreateAt(LocalDateTime.now());
                    polishRepository.save(polish);
                }
            }

            for (EDiamondShape shapeValue : EDiamondShape.values()) {
                DiamondShape shape = shapeRepository.findByShape(shapeValue);
                if (shape == null) {
                    shape = new DiamondShape();
                    shape.setShape(shapeValue);
                    shape.setCreateAt(LocalDateTime.now());
                    shapeRepository.save(shape);
                }
            }

            for (EDiamondClarity clarityValue : EDiamondClarity.values()) {
                DiamondClarity clarity = clarityRepository.findByClarity(clarityValue);
                if (clarity == null) {
                    clarity = new DiamondClarity();
                    clarity.setClarity(clarityValue);
                    clarity.setCreateAt(LocalDateTime.now());
                    clarityRepository.save(clarity);
                }
            }

            for (EJewelryType jewelryTagValue : EJewelryType.values()) {
                JewelryType jewelryType = jewelryTagRepository.findByType(jewelryTagValue);
                if (jewelryType == null) {
                    jewelryType = new JewelryType();
                    jewelryType.setType(jewelryTagValue);
                    jewelryType.setCreateAt(LocalDateTime.now());
                    jewelryTagRepository.save(jewelryType);
                }
            }
        };
    }
}