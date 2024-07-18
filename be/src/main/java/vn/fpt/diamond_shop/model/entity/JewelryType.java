package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EJewelryType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jewelry_type")
public class JewelryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", unique = true)
    private EJewelryType type;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}