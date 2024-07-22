package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddVoucherToAccountRequest {
    @NotNull
    private String code;
}
