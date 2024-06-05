package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.fpt.diamond_shop.constant.EJewelryTag;
import vn.fpt.diamond_shop.model.entity.JewelryTag;

import java.util.List;

public interface IJewelryTagRepository extends JpaRepository<JewelryTag, Long>{
    JewelryTag findByTag(EJewelryTag tag);

    @Query("SELECT j FROM JewelryTag j WHERE j.id IN (:ids)")
    List<JewelryTag> findByIds(@Param("ids") List<Long> ids);
}