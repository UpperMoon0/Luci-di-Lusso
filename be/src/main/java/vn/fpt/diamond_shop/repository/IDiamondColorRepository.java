package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EDiamondColor;
import vn.fpt.diamond_shop.model.entity.DiamondColor;

@Repository
public interface IDiamondColorRepository extends JpaRepository<DiamondColor, Long> {
    DiamondColor findByColor(EDiamondColor color);
}