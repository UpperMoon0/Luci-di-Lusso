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
    private final boolean generateData = false;
    @Bean
    public CommandLineRunner initTables(IDiamondCutRepository cutRepository,
                                        IDiamondColorRepository polishRepository,
                                        IDiamondShapeRepository shapeRepository,
                                        IDiamondClarityRepository clarityRepository,
                                        IJewelryTypeRepository jewelryTagRepository) {
        return args -> {
            if (!generateData) {
                return;
            }

            for (EDiamondCut cutValue : EDiamondCut.values()) {
                DiamondCut cut = cutRepository.findByCut(cutValue);
                if (cut == null) {
                    cut = new DiamondCut();
                    cut.setCut(cutValue);
                    cut.setCreateAt(LocalDateTime.now());
                    cutRepository.save(cut);
                }
            }

            for (EDiamondColor polishValue : EDiamondColor.values()) {
                DiamondColor color = polishRepository.findByColor(polishValue);
                if (color == null) {
                    color = new DiamondColor();
                    color.setColor(polishValue);
                    color.setCreateAt(LocalDateTime.now());
                    polishRepository.save(color);
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
                jewelryTagRepository.findByType(jewelryTagValue).ifPresentOrElse(
                        existingJewelryType -> {
                            // JewelryType exists, no action needed
                        },
                        () -> {
                            // JewelryType not found, create a new one
                            JewelryType newJewelryType = new JewelryType();
                            newJewelryType.setType(jewelryTagValue);
                            newJewelryType.setCreateAt(LocalDateTime.now());
                            jewelryTagRepository.save(newJewelryType);
                        }
                );
            }
        };
    }
}