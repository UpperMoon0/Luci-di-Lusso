package vn.fpt.diamond_shop.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateVoucherRequest {
    @NotNull
    Long id;
    @NotBlank
    String code;
    @NotNull
    Integer discount;
    @NotNull
    LocalDateTime expireAt;
    @NotNull
    Integer price;
}
