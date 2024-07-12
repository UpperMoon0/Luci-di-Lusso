package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EJewelryType;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;
import java.util.Optional;

@Repository
public interface IJewelryTypeRepository extends JpaRepository<JewelryType, Long>{
    Optional<JewelryType> findByType(EJewelryType tag);
    Optional<JewelryType> findFirst();
}