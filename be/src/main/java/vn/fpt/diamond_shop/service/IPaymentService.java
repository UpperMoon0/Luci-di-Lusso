package vn.fpt.diamond_shop.service;

import com.stripe.exception.StripeException;
import vn.fpt.diamond_shop.model.entity.Order;

public interface IPaymentService {
    void createCharge(String token, Integer amount, Order order) throws StripeException;
}
