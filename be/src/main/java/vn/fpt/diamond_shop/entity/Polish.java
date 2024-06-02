package vn.fpt.diamond_shop.entity;

import lombok.*;
import vn.fpt.diamond_shop.constant.EPolish;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "polish")
@NoArgsConstructor
public class Polish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "polish", unique = true)
    private EPolish polish;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
