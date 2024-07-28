package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EDiamondColor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diamond_color")
public class DiamondColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color", unique = true)
    private String color;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status = "ACTIVE";

    @Column(name = "create_at")
    private LocalDateTime createAt = LocalDateTime.now();
}
