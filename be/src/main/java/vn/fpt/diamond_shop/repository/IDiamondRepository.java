package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.Diamond;

import java.util.List;

@Repository
public interface IDiamondRepository extends JpaRepository<Diamond, Long> {
    List<Diamond> findAllById(Long id);
}