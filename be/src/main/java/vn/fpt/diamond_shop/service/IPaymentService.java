package vn.fpt.diamond_shop.service;

import com.stripe.exception.StripeException;

public interface IPaymentService {
    void createCharge(String token, Integer amount) throws StripeException;
}
