package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.Diamond;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IDiamondRepository extends JpaRepository<Diamond, Integer>{
    Optional<Diamond> findById(Long id);
}
