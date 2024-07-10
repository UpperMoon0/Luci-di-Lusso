package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import vn.fpt.diamond_shop.constant.*;

import javax.validation.constraints.NotBlank;

@Data
public class JewelryRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private EJewelryType jewelryType;

    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private Double price;

    @NotBlank
    private Long diamondId;


}
