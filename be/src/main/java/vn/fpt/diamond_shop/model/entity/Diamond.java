package vn.fpt.diamond_shop.model.entity;

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

    @Column
    private int carat;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "clarity_id")
    private Clarity clarity;

    @ManyToOne
    @JoinColumn(name = "cut_id")
    private Cut cut;

    @ManyToOne
    @JoinColumn(name = "polish_id")
    private Polish polish;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "shape_id")
    private Shape shape;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
