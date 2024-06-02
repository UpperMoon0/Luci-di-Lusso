package vn.fpt.diamond_shop.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EColor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private EColor color;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
