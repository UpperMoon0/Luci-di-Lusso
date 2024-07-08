package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.entity.User;

import java.time.LocalDate;

public interface IUserService {
    User getUserByToken(String jwtToken) throws InvalidJwtTokenException;
    void updateCustomerProfile(String jwtToken, String fullName, String address, String phone, String imageUrl, LocalDate dob);
}
