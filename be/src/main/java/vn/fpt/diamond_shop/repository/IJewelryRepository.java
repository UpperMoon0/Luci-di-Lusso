package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;

@Repository
public interface IJewelryRepository extends JpaRepository<Jewelry, Long> {
    List<Jewelry> findAllByType(JewelryType type);
}