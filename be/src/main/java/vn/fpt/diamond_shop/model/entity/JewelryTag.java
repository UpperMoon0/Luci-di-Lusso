package vn.fpt.diamond_shop.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.fpt.diamond_shop.constant.EJewelryTag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "jewelry_tag")
public class JewelryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", unique = true)
    private EJewelryTag tag;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToMany(mappedBy = "jewelryTags")
    private List<Jewelry> jewelries;
}