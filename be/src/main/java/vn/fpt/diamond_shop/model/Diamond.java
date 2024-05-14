package vn.fpt.diamond_shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "DIAMOND")
@Data
@NoArgsConstructor
public class Diamond {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "carat")
    private int carat;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "clarity_id")
    private UUID clarityId;

    @Column(name = "cut_id")
    private UUID cutId;

    @Column(name = "polish_id")
    private UUID polishId;

    @Column(name = "color_id")
    private UUID colorId;

    @Column(name = "shape_id")
    private UUID shapeId;

    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;

    public Diamond(int carat, int quantity, UUID clarityId, UUID cutId, UUID polishId, UUID colorId, UUID shapeId) {
        this.carat = carat;
        this.quantity = quantity;
        this.clarityId = clarityId;
        this.cutId = cutId;
        this.polishId = polishId;
        this.colorId = colorId;
        this.shapeId = shapeId;
        this.createAt = OffsetDateTime.now();
    }
}
