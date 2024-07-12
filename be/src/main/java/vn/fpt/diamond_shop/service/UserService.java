package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EUserRole;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.repository.IUserRepository;
import vn.fpt.diamond_shop.security.JwtTokenProvider;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public UserService(IUserRepository userRepository,
                       JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public User getUserByToken(String jwtToken) throws InvalidJwtTokenException {
        boolean tokenValid = tokenProvider.validateToken(jwtToken);
        if (!tokenValid) {
            throw new InvalidJwtTokenException();
        }
        String username = tokenProvider.getUsernameFromJWT(jwtToken);
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void updateCustomerProfile(String jwtToken, String fullName, String address, String phone, String imageUrl, LocalDate dob) {
        User user = getUserByToken(jwtToken);
        user.setFullName(fullName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setImageUrl(imageUrl);
        user.setDob(dob);
        userRepository.save(user);
    }

    @PostConstruct
    public void createManagerAccount() {
        User managerAccount = userRepository.findByRole(EUserRole.MANAGER);
        if (managerAccount == null) {
            User user = new User();
            user.setUsername("manager");
            user.setPassword("manager");
            user.setPhone("0123456789");
            user.setFullName("Manager");
            user.setRole(EUserRole.MANAGER);
            userRepository.save(user);
        }
    }

    @Override
    public List<Integer> getCustomerCreationStatistics() {
        // Fetch users with CUSTOMER role created in the last 30 days
        List<User> users = userRepository.findAllByRoleAndCreateAtBetween(EUserRole.CUSTOMER, LocalDateTime.now().minusDays(30), LocalDateTime.now());
        Map<LocalDate, Integer> dailyUserCreations = new HashMap<>();

        for (User user : users) {
            LocalDate date = user.getCreateAt().toLocalDate();
            dailyUserCreations.merge(date, 1, Integer::sum);
        }

        List<Integer> customerCreationStatistics = new ArrayList<>();
        LocalDate start = LocalDate.now().minusDays(29); // Start from 30 days ago
        for (int i = 0; i < 30; i++) {
            customerCreationStatistics.add(dailyUserCreations.getOrDefault(start.plusDays(i), 0));
        }

        return customerCreationStatistics;
    }

    @Override
    public List<User> getAllDeliverers() {
        return userRepository.findAllByRole(EUserRole.DELIVERER);
    }
}
