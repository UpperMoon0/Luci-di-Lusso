package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiamondClarity clarity;

    @ManyToOne
    @JoinColumn(name = "cut_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiamondCut cut;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiamondColor color;

    @Column(name = "carat")
    private Float carat;

    @ManyToOne
    @JoinColumn(name = "shape_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DiamondShape shape;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
