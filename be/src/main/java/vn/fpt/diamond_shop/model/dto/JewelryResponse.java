package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JewelryResponse extends CommonResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private String type;
    private String diamondCut;
    private String diamondClarity;
    private String diamondColor;
    private String diamondShape;
}
