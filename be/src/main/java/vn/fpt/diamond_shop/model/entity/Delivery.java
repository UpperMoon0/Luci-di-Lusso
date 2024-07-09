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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "deliverer_id")
    private User deliverer;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "status")
    private EOrderStatus status;

    @Column(name = "create_at")
    private LocalDateTime createAt;

}
