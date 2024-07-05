package vn.fpt.diamond_shop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jewelry_id")
    private Jewelry jewelry;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private JewelrySize size;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}