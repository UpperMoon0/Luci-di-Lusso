package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DiamondRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private int quantity;

    @NotBlank
    private Long clarityId;

    @NotBlank
    private Long cutId;

    @NotBlank
    private Long colorId;

    @NotBlank
    private Float carat;

    @NotBlank
    private Long shapeId;

}
