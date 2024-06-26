package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.Jewelry;

import java.util.List;

@Repository
public interface IJewelryRepository extends JpaRepository<Jewelry, Long> {
    @Query("SELECT SUM(j.price) FROM Jewelry j WHERE j.id IN (:ids)")
    Double getTotalPriceByIdList(@Param("ids") List<Long> ids);
    List<Jewelry> findAll();
}