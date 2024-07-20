package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDetailsResponse extends CommonResponse {
    private String customerName;
    private Double totalPrice;
    private String createAt;
    private List<JewelryInOrderDTO> productList = new ArrayList<>();

    public OrderDetailsResponse(Order order, List<OrderItem> orderItems) {
        this.customerName = order.getCustomer().getFullName();
        this.totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        this.createAt = order.getCreateAt().format(formatter);

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
                  Integer price,
                  Integer quantity,
                  String type,
                  String imageUrl) {}
