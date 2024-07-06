package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EDiamondShape;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "diamond_shape")
public class DiamondShape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shape", unique = true)
    private EDiamondShape shape;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
