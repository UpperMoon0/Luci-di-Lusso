package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constant.ECartRequestType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CartRequest {
    @NotNull
    private ECartRequestType type;
    private Long productId;
    private int quantity;
}
