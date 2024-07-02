package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EDiamondShape;
import vn.fpt.diamond_shop.model.entity.DiamondShape;

@Repository
public interface IDiamondShapeRepository extends JpaRepository<DiamondShape, Long> {
    DiamondShape findByShape(EDiamondShape shapeValue);
}