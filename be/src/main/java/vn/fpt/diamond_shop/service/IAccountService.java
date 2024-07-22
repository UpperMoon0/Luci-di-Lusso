package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.LoginRequest;
import vn.fpt.diamond_shop.model.dto.UpdateCustomerProfileRequest;
import vn.fpt.diamond_shop.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Optional<Account> findAccountByToken(String jwtToken) throws InvalidJwtTokenException;
    List<Account> findAllDeliverers();
    List<Integer> findCustomerCreationStatistics();
    void updateCustomerProfile(String jwtToken, UpdateCustomerProfileRequest request);
    String login(LoginRequest request);
    void addPoint(String jwtToken, double totalPrice);
    int getCustomerPoints(Long customerId);
}
