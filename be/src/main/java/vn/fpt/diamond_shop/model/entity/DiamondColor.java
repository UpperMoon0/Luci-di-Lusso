package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EDiamondColor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "diamond_color")
@NoArgsConstructor
public class DiamondColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", unique = true)
    private EDiamondColor color;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
