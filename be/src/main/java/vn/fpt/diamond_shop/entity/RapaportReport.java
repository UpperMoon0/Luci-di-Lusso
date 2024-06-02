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
@Table(name = "rapaport_report")
@NoArgsConstructor
public class RapaportReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clarity_id")
    private Long clarityId;

    @Column(name = "color_id")
    private Long colorId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "cara_from")
    private Integer caraF;

    @Column(name = "cara_to")
    private Integer caraT;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
