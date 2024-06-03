package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EShape;
import vn.fpt.diamond_shop.model.entity.Shape;

@Repository
public interface IShapeRepository extends JpaRepository<Shape, Long> {
    Shape findByShape(EShape shapeValue);
}