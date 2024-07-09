package vn.fpt.diamond_shop.model.entity;

import lombok.Data;

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

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
