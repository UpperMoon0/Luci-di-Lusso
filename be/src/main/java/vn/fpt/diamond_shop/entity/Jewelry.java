package vn.fpt.diamond_shop.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "jewelry")
@NoArgsConstructor
public class Jewelry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "diamond_id")
    private Long diamondId;

    @Column(name = "need_sizing")
    private Boolean needSizing;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}