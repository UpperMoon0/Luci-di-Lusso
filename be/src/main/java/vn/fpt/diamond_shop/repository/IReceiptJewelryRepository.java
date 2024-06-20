package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.diamond_shop.model.entity.ReceiptJewelry;

public interface IReceiptJewelryRepository extends JpaRepository<ReceiptJewelry, Long> {
}
