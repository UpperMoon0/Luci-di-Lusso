package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JewelryRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String jewelryType;

    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private Double price;

    @NotBlank
    private Long diamondId;
}
