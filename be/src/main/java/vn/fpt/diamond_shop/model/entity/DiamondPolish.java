package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EDiamondPolish;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "diamond_polish")
@NoArgsConstructor
public class DiamondPolish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "polish", unique = true)
    private EDiamondPolish polish;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
