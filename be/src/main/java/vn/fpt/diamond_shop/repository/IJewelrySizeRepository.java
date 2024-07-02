package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.diamond_shop.model.entity.JewelrySize;

public interface IJewelrySizeRepository extends JpaRepository<JewelrySize, Long> {
    JewelrySize findById(long id);
}
