package vn.fpt.diamond_shop.model.dto;

import lombok.*;
import vn.fpt.diamond_shop.model.entity.CartItem;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class GetCartResponse extends CommonResponse {
    private final List<CartItemDTO> cartItems;
    private final Double totalPrice;
    private final Integer totalItems;

    public GetCartResponse(List<CartItem> cartItems) {
        this.cartItems = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        for (CartItem cartItem : cartItems) {
            this.cartItems.add(
                    new CartItemDTO(
                            cartItem.getId(),
                            cartItem.getJewelry().getName(),
                            cartItem.getJewelry().getImageUrl(),
                            cartItem.getSize().getSize() + " " + cartItem.getSize().getUnit(),
                            cartItem.getJewelry().getPrice(),
                            cartItem.getQuantity(),
                            cartItem.getCreateAt().format(formatter)
                    )
            );
        }

        totalPrice = cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getJewelry().getPrice() * cartItem.getQuantity())
                .sum();

        totalItems = cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}

record CartItemDTO(Long id, String name, String imageUrl, String size, Double price, Integer quantity, String createAt) {}