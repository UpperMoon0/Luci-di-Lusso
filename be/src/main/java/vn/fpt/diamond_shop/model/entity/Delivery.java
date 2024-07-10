package vn.fpt.diamond_shop.model.entity;

import lombok.Data;
import vn.fpt.diamond_shop.constant.EOrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "deliverer_id")
    private User deliverer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private EOrderStatus status;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
