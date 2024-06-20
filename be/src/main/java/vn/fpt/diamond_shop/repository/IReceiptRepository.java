package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.diamond_shop.model.entity.Receipt;

public interface IReceiptRepository extends JpaRepository<Receipt, Long> {
}
