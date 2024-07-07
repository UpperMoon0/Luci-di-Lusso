package vn.fpt.diamond_shop.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService implements IPaymentService {

    @Value("${STRIPE_SECRET}")
    private String stripeApiKey;

    @Override
    public void createCharge(String token, Integer totalPriceInCent) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", totalPriceInCent);
        params.put("currency", "usd");
        params.put("source", token);
        params.put("description", "My First Test Charge (created for API docs)");
        Charge charge = Charge.create(params);
        System.out.println(charge);
    }
}