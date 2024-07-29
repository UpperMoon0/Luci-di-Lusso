package vn.fpt.diamond_shop.repository;

import vn.fpt.diamond_shop.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByRole(String role);
    Optional<Account> findByUsernameAndPassword(String username, String password);
    Boolean existsByUsername(String username);
    List<Account> findAllByRole(String role);
    List<Account> findAllByRoleAndCreateAtBetween(String role, LocalDateTime createAt, LocalDateTime createAt2);
}
