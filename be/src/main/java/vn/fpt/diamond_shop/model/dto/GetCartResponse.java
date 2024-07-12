package vn.fpt.diamond_shop.model.dto;

import lombok.*;
import vn.fpt.diamond_shop.model.entity.CartItem;
import vn.fpt.diamond_shop.service.IJewelryService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class GetCartResponse extends CommonResponse {
    private final List<CartItemDTO> cartItems;
    private final int totalPrice;
    private final int totalItems;

    public GetCartResponse(List<CartItem> cartItems, IJewelryService productService) {
        this.cartItems = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        for (CartItem cartItem : cartItems) {
            this.cartItems.add(
                    new CartItemDTO(
                            cartItem.getId(),
                            cartItem.getJewelry().getName(),
                            cartItem.getJewelry().getImageUrl(),
                            cartItem.getJewelrySize().getSize() + " " + cartItem.getJewelrySize().getUnit(),
                            productService.calculateJewelryPriceWithSize(cartItem.getJewelry(), cartItem.getJewelrySize()),
                            cartItem.getQuantity(),
                            cartItem.getCreateAt().format(formatter)
                    )
            );
        }

        totalPrice = this.cartItems.stream()
                .mapToInt(cartItem -> cartItem.price() * cartItem.quantity())
                .sum();

        totalItems = cartItems.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}

record CartItemDTO(Long id,
                   String name,
                   String imageUrl,
                   String size,
                   Integer price,
                   Integer quantity,
                   String createAt)
{ }