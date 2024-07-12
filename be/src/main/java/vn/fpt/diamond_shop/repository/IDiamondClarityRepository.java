package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EDiamondClarity;
import vn.fpt.diamond_shop.model.entity.DiamondClarity;

import java.util.Optional;

@Repository
public interface IDiamondClarityRepository extends JpaRepository<DiamondClarity, Long> {
    Optional<DiamondClarity> findByClarity(EDiamondClarity clarityValue);
}