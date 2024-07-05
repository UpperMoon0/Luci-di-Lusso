package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.diamond_shop.model.entity.JewelrySize;

import java.util.Optional;

public interface IJewelrySizeRepository extends JpaRepository<JewelrySize, Long> {
}
