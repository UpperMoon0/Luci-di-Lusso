package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDetailsResponse extends CommonResponse {
    private String customerName;
    private Double totalPrice;
    private LocalDateTime createAt;
    private String formattedCreateAt;
    private List<JewelryInOrderDTO> productList = new ArrayList<>();

    public OrderDetailsResponse(Order order, List<OrderItem> orderItems) {
        this.customerName = order.getUser().getFullName();
        this.totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        this.createAt = order.getCreateAt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        this.formattedCreateAt = this.createAt.format(formatter);

        for (OrderItem item : orderItems) {
            Jewelry jewelry = item.getJewelry();
            JewelrySize size = item.getJewelrySize();
            JewelryInOrderDTO jewelryInOrderDTO = new JewelryInOrderDTO(
                    jewelry.getName(),
                    size.getSize() + " " + size.getUnit(),
                    item.getPrice(),
                    item.getQuantity(),
                    jewelry.getType().getType().getValue(),
                    jewelry.getImageUrl()
            );

            productList.add(jewelryInOrderDTO);
        }
    }
}

record JewelryInOrderDTO(String name,
                  String size,
                  Double price,
                  Integer quantity,
                  String type,
                  String imageUrl) {}
