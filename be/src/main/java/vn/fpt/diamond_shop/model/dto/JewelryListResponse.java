package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.ToString;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.service.IJewelryService;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class JewelryListResponse extends CommonResponse {
    private final List<JewelryDTO> jewelries = new ArrayList<>();

    public JewelryListResponse(List<Jewelry> jewelries, IJewelryService jewelryService) {
        for (Jewelry jewelry : jewelries) {
            JewelryDTO jewelryDTO = new JewelryDTO(
                    jewelry,
                    jewelryService.calculateJewelryPrice(jewelry),
                    jewelryService.isJewelryInStock(jewelry)
            );
            this.jewelries.add(jewelryDTO);
        }
    }
}

record JewelryDTO(
        Jewelry jewelry,
        int price,
        boolean isInStock
) { }
