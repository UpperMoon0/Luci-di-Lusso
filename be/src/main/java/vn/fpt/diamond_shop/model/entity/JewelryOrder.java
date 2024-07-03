package vn.fpt.diamond_shop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "jewelry_order")
public class JewelryOrder {
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

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}