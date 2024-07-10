package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.JewelryType;

import java.util.List;
import java.util.Optional;

@Repository
public interface IJewelryRepository extends JpaRepository<Jewelry, Long> {
    @Query("SELECT SUM(j.price) FROM Jewelry j WHERE j.id IN (:ids)")
    Double getTotalPriceByIdList(@Param("ids") List<Long> ids);
    List<Jewelry> findAllByType(Optional<JewelryType> jewelryTag);
    List<Jewelry> findAllByNameContaining(String title);
}