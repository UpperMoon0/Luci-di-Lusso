package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.constant.EUserRole;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.LoginRequest;
import vn.fpt.diamond_shop.model.dto.RegisterRequest;
import vn.fpt.diamond_shop.model.dto.UpdateCustomerProfileRequest;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.model.entity.Customer;
import vn.fpt.diamond_shop.repository.IAccountRepository;
import vn.fpt.diamond_shop.repository.ICustomerRepository;
import vn.fpt.diamond_shop.security.JwtTokenProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AccountService implements IAccountService {
    private final IAccountRepository userRepository;
    private final ICustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public Optional<Account> findAccountByToken(String jwtToken) throws InvalidJwtTokenException {
        boolean tokenValid = tokenProvider.validateToken(jwtToken);
        if (!tokenValid) {
            throw new InvalidJwtTokenException();
        }
        String username = tokenProvider.getUsernameFromJWT(jwtToken);
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateCustomerProfile(String jwtToken, UpdateCustomerProfileRequest request) throws RuntimeException {
        String fullName = request.getFullName();
        String address = request.getAddress();
        String phone = request.getPhone();
        String imageUrl = request.getImageUrl();
        LocalDate dob = request.getDob();

        Account account = findAccountByToken(jwtToken).orElse(null);

        if (account == null) {
            throw new RuntimeException("User not found");
        }

        Customer customer = account.getCustomer();

        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        customer.setFullName(fullName);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setImageUrl(imageUrl);
        customer.setDob(dob);
        customerRepository.save(customer);
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPrincipal(),
                        request.getCredentials()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    @Override
    public List<Integer> findCustomerCreationStatistics() {
        // Fetch users with CUSTOMER role created in the last 30 days
        List<Account> users = userRepository.findAllByRoleAndCreateAtBetween(EUserRole.CUSTOMER, LocalDateTime.now().minusDays(30), LocalDateTime.now());
        Map<LocalDate, Integer> dailyUserCreations = new HashMap<>();

        for (Account user : users) {
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
    public List<Account> findAllDeliverers() {
        return userRepository.findAllByRole(EUserRole.DELIVERER);
    }

    public void register(RegisterRequest registerRequest) throws RuntimeException {
        String email = registerRequest.getEmail();
        String username = registerRequest.getUsername();

        if(customerRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already in use");
        } else if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already in use");
        } else {
            Account user = Account.builder()
                    .username(username)
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .provider(registerRequest.getProvider())
                    .role(EUserRole.CUSTOMER)
                    .createAt(LocalDateTime.now())
                    .build();

            userRepository.save(user);

            Customer customer = Customer.builder()
                    .fullName(registerRequest.getFullName())
                    .email(email)
                    .phone(registerRequest.getPhone())
                    .address(registerRequest.getAddress())
                    .dob(registerRequest.getDob())
                    .createAt(LocalDateTime.now())
                    .account(user)
                    .build();

            customerRepository.save(customer);
        }
    }

    @Override
    public void addPoint(String jwtToken, double totalPrice) {
        Account account = findAccountByToken(jwtToken).orElseThrow();
        Customer customer = account.getCustomer();
        customer.setPoint(customer.getPoint() + ((int) totalPrice * 10/100));
        customerRepository.save(customer);
    }

    @Override
    public int getCustomerPoints(Long customerId) {
        return customerRepository.getById(customerId).getPoint();
    }




}
