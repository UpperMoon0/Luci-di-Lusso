package vn.fpt.diamond_shop.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JewelryType type;

    @Column(name = "unit", length = 10, nullable = false)
    private String unit;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}
