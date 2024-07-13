package vn.fpt.diamond_shop.repository;

import vn.fpt.diamond_shop.constant.EUserRole;
import vn.fpt.diamond_shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    User findByRole(EUserRole role);
    List<User> findAllByRole(EUserRole role);
    List<User> findAllByRoleAndCreateAtBetween(EUserRole role, LocalDateTime createAt, LocalDateTime createAt2);
}
