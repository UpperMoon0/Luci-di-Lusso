package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EDiamondShape;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diamond_shape")
public class DiamondShape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shape", unique = true)
    private String shape;

    @Column(name = "price_multiplier")
    private Float priceMultiplier;

    @Column(name = "status")
    private String status = "ACTIVE";

    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();
}
