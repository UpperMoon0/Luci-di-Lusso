package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.Optional;

@Repository
public interface IJewelryTypeRepository extends JpaRepository<JewelryType, Long>{
    Optional<JewelryType> findByType(String tag);
    Optional<JewelryType> findFirstBy();
}