package vn.fpt.diamond_shop.model.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
@Table(name = "`customer`")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "point")
    private Integer point;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @OneToOne
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_voucher",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "voucher_id")
    )
    private Set<Voucher> vouchers = new HashSet<>();
}
