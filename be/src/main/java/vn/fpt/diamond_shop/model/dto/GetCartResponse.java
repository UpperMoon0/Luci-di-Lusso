package vn.fpt.diamond_shop.model.dto;

import lombok.*;
import vn.fpt.diamond_shop.model.entity.CartItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class GetCartResponse extends CommonResponse {
    private final List<CartItemDTO> cartItems;
    private final Double totalPrice;

    public GetCartResponse(List<CartItem> cartItems) {
        this.cartItems = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        for (CartItem cartItem : cartItems) {
            this.cartItems.add(
                    new CartItemDTO(
                            cartItem.getId(),
                            cartItem.getJewelry().getName(),
                            cartItem.getJewelry().getImageUrl(),
                            cartItem.getJewelry().getPrice(),
                            cartItem.getQuantity(),
                            cartItem.getCreateAt().format(formatter)
                    )
            );
        }

        totalPrice = cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getJewelry().getPrice() * cartItem.getQuantity())
                .sum();
    }
}

@Getter
class CartItemDTO {
    private final Long id;
    private final String name;
    private final String imageUrl;
    private final Double price;
    private final Integer quantity;
    private final String createAt;

    public CartItemDTO(Long id,
                       String name,
                       String imageUrl,
                       Double price,
                       Integer quantity,
                       String createAt) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.createAt = createAt;
    }
}