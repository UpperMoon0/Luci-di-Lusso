package vn.fpt.diamond_shop.model.entity;
import lombok.Data;
import vn.fpt.diamond_shop.constant.EAuthProvider;
import vn.fpt.diamond_shop.constant.EUserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

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

    @Column(name = "create_at")
    private LocalDateTime createAt;
  
    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private EAuthProvider provider;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role", nullable = false)
//    private EUserRole role;

}
