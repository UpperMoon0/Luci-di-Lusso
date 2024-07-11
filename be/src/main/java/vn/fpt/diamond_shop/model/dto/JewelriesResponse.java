package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.ToString;
import vn.fpt.diamond_shop.model.entity.Jewelry;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class JewelriesResponse extends CommonResponse {
    private final List<JewelryDTO> jewelries = new ArrayList<>();

    public void addJewelry(Jewelry jewelry) {
        jewelries.add(new JewelryDTO(jewelry.getId(), jewelry.getName(), jewelry.getImageUrl(), jewelry.getPrice(), jewelry.getType().getType().getValue()));
    }
}

record JewelryDTO(Long id, String name, String imageUrl, Double price, String type) {
}