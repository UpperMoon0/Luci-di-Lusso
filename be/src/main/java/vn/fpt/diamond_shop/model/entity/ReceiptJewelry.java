package vn.fpt.diamond_shop.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "receipt_jewelry")
public class ReceiptJewelry {
    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "receipt_id")
    private Long receiptId;

    @Column(name = "jewelry_id")
    private Long jewelryId;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}