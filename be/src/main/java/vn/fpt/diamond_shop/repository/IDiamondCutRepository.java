package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EDiamondCut;
import vn.fpt.diamond_shop.model.entity.DiamondCut;

@Repository
public interface IDiamondCutRepository extends JpaRepository<DiamondCut, Long> {
    DiamondCut findByCut(EDiamondCut cut);
}