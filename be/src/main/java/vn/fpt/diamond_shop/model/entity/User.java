package vn.fpt.diamond_shop.model.entity;
import lombok.Getter;
import lombok.Setter;
import vn.fpt.diamond_shop.constant.EAuthProvider;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "`user`", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private EAuthProvider provider;
}
