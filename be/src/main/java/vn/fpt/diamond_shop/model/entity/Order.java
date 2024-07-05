package vn.fpt.diamond_shop.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}