package vn.fpt.diamond_shop.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import vn.fpt.diamond_shop.constants.DiamondShapeEnum;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "shape")
public class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shape", unique = true)
    private DiamondShapeEnum shape;

    @Column(name = "image")
    private UUID imageMetadata;

    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;
}
