package vn.fpt.diamond_shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constant.ECut;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cut")
public class Cut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "cut", unique = true)
    private ECut cut;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
