package vn.fpt.diamond_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fpt.diamond_shop.model.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByEmail(String email);
}
