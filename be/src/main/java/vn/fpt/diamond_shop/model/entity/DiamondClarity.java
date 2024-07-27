package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EDiamondClarity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diamond_clarity")
public class DiamondClarity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clarity", unique = true)
    private String clarity;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
