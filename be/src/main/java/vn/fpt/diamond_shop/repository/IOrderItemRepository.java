package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpt.diamond_shop.model.entity.Jewelry;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByJewelry(Jewelry jewelry);
    List<OrderItem> findAllByOrder(Order order);
    List<OrderItem> findAllByCreateAtBetween(LocalDateTime createAt, LocalDateTime createAt2);
}
