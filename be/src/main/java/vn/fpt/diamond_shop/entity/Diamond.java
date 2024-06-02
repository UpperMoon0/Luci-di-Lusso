package vn.fpt.diamond_shop.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "diamond")
public class Diamond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carat")
    private int carat;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "clarity_id")
    private Long clarityId;

    @Column(name = "cut_id")
    private Long cutId;

    @Column(name = "polish_id")
    private Long polishId;

    @Column(name = "color_id")
    private Long colorId;

    @Column(name = "shape_id")
    private Long shapeId;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
