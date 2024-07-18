package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.LoginRequest;
import vn.fpt.diamond_shop.model.dto.UpdateCustomerProfileRequest;
import vn.fpt.diamond_shop.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Optional<Account> getUserByToken(String jwtToken) throws InvalidJwtTokenException;
    List<Account> getAllDeliverers();
    List<Integer> getCustomerCreationStatistics();
    void updateCustomerProfile(String jwtToken, UpdateCustomerProfileRequest request);
    String login(LoginRequest request);
}
