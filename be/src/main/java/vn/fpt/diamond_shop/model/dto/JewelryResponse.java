package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JewelryResponse extends CommonResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final String imageUrl;
    private final Double price;
    private final String type;
    private final String diamondCut;
    private final String diamondClarity;
    private final String diamondColor;
    private final String diamondShape;
    private final List<SizeDTO> sizes;

    public JewelryResponse(Jewelry jewelry, List<JewelrySize> sizes) {
        this.id = jewelry.getId();
        this.name = jewelry.getName();
        this.description = jewelry.getDescription();
        this.imageUrl = jewelry.getImageUrl();
        this.price = jewelry.getPrice();
        this.type = jewelry.getType().getType().getValue();
        this.diamondCut = jewelry.getDiamond().getCut().getCut().getValue();
        this.diamondClarity = jewelry.getDiamond().getClarity().getClarity().name();
        this.diamondColor = jewelry.getDiamond().getColor().getColor().name();
        this.diamondShape = jewelry.getDiamond().getShape().getShape().getValue();
        List<SizeDTO> list = new ArrayList<>();
        for (JewelrySize size : sizes) {
            SizeDTO sizeDTO = new SizeDTO(size.getSizeIndex(), size.getSize());
            list.add(sizeDTO);
        }
        this.sizes = list;
    }
}

record SizeDTO(Integer index, Float size) {}
