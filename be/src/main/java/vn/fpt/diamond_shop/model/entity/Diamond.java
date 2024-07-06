package vn.fpt.diamond_shop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "diamond")
public class Diamond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "clarity_id")
    private DiamondClarity clarity;

    @ManyToOne
    @JoinColumn(name = "cut_id")
    private DiamondCut cut;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private DiamondColor color;

    @Column(name = "carat")
    private Float carat;

    @ManyToOne
    @JoinColumn(name = "shape_id")
    private DiamondShape shape;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
