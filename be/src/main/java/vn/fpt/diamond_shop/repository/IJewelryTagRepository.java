package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.diamond_shop.constant.EJewelryTag;
import vn.fpt.diamond_shop.model.entity.JewelryTag;

public interface IJewelryTagRepository extends JpaRepository<JewelryTag, Long>{
    JewelryTag findByTag(EJewelryTag tag);
}
