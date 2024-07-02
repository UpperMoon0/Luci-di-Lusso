package vn.fpt.diamond_shop.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "jewelry_size")
public class JewelrySize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size_index")
    private Integer sizeIndex;

    @Column(name = "size")
    private Float size;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
