package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EColor;
import vn.fpt.diamond_shop.model.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Color findByColor(EColor color);
}