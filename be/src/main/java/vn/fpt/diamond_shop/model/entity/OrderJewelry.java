package vn.fpt.diamond_shop.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order_jewelry")
public class OrderJewelry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long receiptId;

    @Column(name = "jewelry_id")
    private Long jewelryId;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}