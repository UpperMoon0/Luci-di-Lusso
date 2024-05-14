package vn.fpt.diamond_shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "RAPAPORT_REPORT")
@NoArgsConstructor
public class RapaportReport {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "clarity_id")
    private UUID clarityId;

    @Column(name = "color_id")
    private UUID colorId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "cara_from")
    private int caraF;

    @Column(name = "cara_to")
    private int caraT;

    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;

    public RapaportReport(UUID clarityId, UUID colorId, Boolean isActive, int caraF, int caraT) {
        this.clarityId = clarityId;
        this.colorId = colorId;
        this.isActive = isActive;
        this.caraF = caraF;
        this.caraT = caraT;
        this.createAt = OffsetDateTime.now();
    }
}
