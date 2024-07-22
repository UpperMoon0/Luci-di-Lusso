package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.JewelryCollection;

@Repository
public interface IJewelryCollectionRepository extends JpaRepository<JewelryCollection, Long>{}
