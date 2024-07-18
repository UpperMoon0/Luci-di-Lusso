package vn.fpt.diamond_shop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    // Discount in percentage
    @Column
    private Integer discount;

    // Price in point
    @Column
    private Integer price;

    @Column
    private LocalDateTime createAt;

    @Column
    private LocalDateTime expireAt;

    @ManyToMany(mappedBy = "vouchers")
    private Set<Customer> customers = new HashSet<>();
}
