package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.OrderJewelry;

@Repository
public interface IOrderJewelryRepository extends JpaRepository<OrderJewelry, Long> {
}