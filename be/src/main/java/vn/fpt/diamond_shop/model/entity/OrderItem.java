package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "jewelry_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Jewelry jewelry;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JewelrySize size;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}