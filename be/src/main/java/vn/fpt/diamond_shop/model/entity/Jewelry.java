package vn.fpt.diamond_shop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "jewelry")
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

    @OneToOne
    @JoinColumn(name = "diamond_id")
    private Diamond diamond;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private JewelryType type;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}