package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.diamond_shop.model.entity.JewelrySize;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;
import java.util.Optional;

public interface IJewelrySizeRepository extends JpaRepository<JewelrySize, Long> {
    List<JewelrySize> findAllByType(JewelryType type);
    Optional<JewelrySize> findFirstByType(JewelryType type);
    Optional<JewelrySize> findFirst();
}
