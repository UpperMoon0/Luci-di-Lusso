package vn.fpt.diamond_shop.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "jewelry_jewelry_tag")
public class JewelryJewelryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jewelry_id")
    private Long jewelryId;

    @Column(name = "jewelry_tag_id")
    private Long jewelryTagId;

    @Column(name = "create_at")
    private Long createAt;
}
