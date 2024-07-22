package vn.fpt.diamond_shop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import vn.fpt.diamond_shop.constant.EAuthProvider;
import vn.fpt.diamond_shop.constant.EUserRole;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`account`")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "create_at")
    private LocalDateTime createAt;
  
    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private EAuthProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EUserRole role;

    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private Customer customer;
}
