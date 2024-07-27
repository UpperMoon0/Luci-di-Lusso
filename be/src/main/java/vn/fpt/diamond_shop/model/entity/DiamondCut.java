package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EDiamondCut;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diamond_cut")
public class DiamondCut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cut", unique = true)
    private String cut;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
