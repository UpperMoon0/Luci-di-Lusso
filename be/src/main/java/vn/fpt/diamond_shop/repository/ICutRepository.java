package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.ECut;
import vn.fpt.diamond_shop.model.entity.Cut;

@Repository
public interface ICutRepository extends JpaRepository<Cut, Long> {
    Cut findByCut(ECut cut);
}