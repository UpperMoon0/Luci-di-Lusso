package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.constant.EPolish;
import vn.fpt.diamond_shop.model.entity.Polish;

@Repository
public interface IPolishRepository extends JpaRepository<Polish, Long> {
    Polish findByPolish(EPolish polishValue);
}