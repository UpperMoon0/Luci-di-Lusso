package vn.fpt.diamond_shop.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import vn.fpt.diamond_shop.constants.DiamondColorEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", unique = true)
    private DiamondColorEnum color;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
