package vn.fpt.diamond_shop.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constant.EDiamondCut;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "diamond_cut")
public class DiamondCut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "cut", unique = true)
    private EDiamondCut cut;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
