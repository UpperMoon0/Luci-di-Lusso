package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.CartItem;

import java.util.List;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCustomerId(Long userId);
    List<CartItem> findByCustomerIdAndJewelryIdAndJewelrySizeId(Long user_id, Long jewelry_id, Long size_id);
    void deleteAllByCustomerId(Long userId);
}
