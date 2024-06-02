package vn.fpt.diamond_shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constants.DiamondCutEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cut")
public class Cut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "cut", unique = true)
    private DiamondCutEnum cut;

    @Column(name = "point")
    private Integer point;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
