package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constant.ECartRequestType;

import javax.validation.constraints.NotNull;

/**
 * A data transfer object representing a request related to a shopping cart.
 */
@Getter
@Setter
@ToString
public class CartRequest {
    /**
     * The type of the cart request, indicating the action to be performed.
     */
    @NotNull
    private ECartRequestType type;

    /**
     * The ID of the product affected by the cart request.
     * Can be ignored if the request type is REMOVE_ALL or GET.
     */
    private Long productId;

    /**
     * The quantity of the product affected by the cart request.
     * Can be ignored if the request type is REMOVE_ALL or GET.
     */
    private int quantity;
}
