package vn.fpt.diamond_shop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

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

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "role", nullable = false)
    private String role;

    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private Customer customer;
}
