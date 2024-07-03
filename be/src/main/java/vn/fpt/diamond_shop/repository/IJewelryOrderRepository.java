package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelryOrder;

import java.util.Optional;

@Repository
public interface IJewelryOrderRepository extends JpaRepository<JewelryOrder, Long> {
    Optional<JewelryOrder> findByJewelry(Jewelry jewelry);
}
