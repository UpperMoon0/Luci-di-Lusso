package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.DiamondShape;

import java.util.Optional;

@Repository
public interface IDiamondShapeRepository extends JpaRepository<DiamondShape, Long> {
    Optional<DiamondShape> findByShape(String shapeValue);
}