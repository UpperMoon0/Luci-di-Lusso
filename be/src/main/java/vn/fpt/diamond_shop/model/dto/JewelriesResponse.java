package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.ToString;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.service.IJewelryService;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class JewelriesResponse extends CommonResponse {
    private final List<JewelryDTO> jewelries = new ArrayList<>();

    public JewelriesResponse(List<Jewelry> jewelries, IJewelryService jewelryService) {
        for (Jewelry jewelry : jewelries) {
            JewelryDTO jewelryDTO = new JewelryDTO(
                    jewelry.getId(),
                    jewelry.getName(),
                    jewelry.getSettingPrice(),
                    jewelry.getLaborCost(),
                    jewelry.getType().getId(),
                    jewelry.getType().getType().name(),
                    jewelry.getDiamond().getId(),
                    jewelryService.calculateJewelryPrice(jewelry),
                    jewelry.getDescription(),
                    jewelry.getImageUrl()
            );
            this.jewelries.add(jewelryDTO);
        }
    }
}

record JewelryDTO(
        long id,
        String name,
        int settingPrice,
        int laborCost,
        long typeId,
        String type,
        long diamondId,
        int price,
        String description,
        String imageUrl
) { }
