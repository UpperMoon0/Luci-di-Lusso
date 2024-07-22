package vn.fpt.diamond_shop.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @JsonManagedReference
    @ManyToMany(mappedBy = "vouchers")
    private List<Customer> customers = new ArrayList<>();

    @Column(name = "status")
    private String status;
}
