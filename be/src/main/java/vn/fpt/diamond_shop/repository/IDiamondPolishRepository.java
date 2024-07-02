package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EDiamondPolish;
import vn.fpt.diamond_shop.model.entity.DiamondPolish;

@Repository
public interface IDiamondPolishRepository extends JpaRepository<DiamondPolish, Long> {
    DiamondPolish findByPolish(EDiamondPolish polishValue);
}