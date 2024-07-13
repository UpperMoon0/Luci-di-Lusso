package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.service.IJewelryService;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JewelryResponse extends CommonResponse {
    private final long id;
    private final String name;
    private final String description;
    private final String imageUrl;
    private final int price;
    private final String type;
    private final float diamondCarat;
    private final long diamondId;
    private final String diamondCut;
    private final String diamondClarity;
    private final String diamondColor;
    private final String diamondShape;
    private final List<SizeDTO> sizes;

    public JewelryResponse(Jewelry jewelry, List<JewelrySize> sizes, IJewelryService jewelryService) {
        this.id = jewelry.getId();
        this.name = jewelry.getName();
        this.description = jewelry.getDescription();
        this.imageUrl = jewelry.getImageUrl();
        this.price = jewelryService.calculateJewelryPrice(jewelry);
        this.type = jewelry.getType().getType().getValue();
        this.diamondId = jewelry.getDiamond().getId();
        this.diamondCarat = jewelry.getDiamond().getCarat();
        this.diamondCut = jewelry.getDiamond().getCut().getCut().getValue();
        this.diamondClarity = jewelry.getDiamond().getClarity().getClarity().name();
        this.diamondColor = jewelry.getDiamond().getColor().getColor().name();
        this.diamondShape = jewelry.getDiamond().getShape().getShape().getValue();
        List<SizeDTO> list = new ArrayList<>();
        for (JewelrySize size : sizes) {
            SizeDTO sizeDTO = new SizeDTO(size.getId(), size.getSize(), size.getUnit());
            list.add(sizeDTO);
        }
        this.sizes = list;
    }
}

record SizeDTO(Long id, Float size, String unit) {}
