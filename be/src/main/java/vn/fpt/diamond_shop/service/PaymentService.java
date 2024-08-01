package vn.fpt.diamond_shop.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.Order;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService implements IPaymentService {

    @Value("${STRIPE_SECRET}")
    private String stripeApiKey;

    @Override
    public void createCharge(String token, Integer totalPriceInCent, Order order) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", totalPriceInCent);
        params.put("currency", "usd");
        params.put("source", token);
        params.put("description", "Luci di Lusso: Charge for " + order.getCustomer().getFullName());
        Charge charge = Charge.create(params);
        String receiptUrl = charge.getReceiptUrl();
        order.setReceiptUrl(receiptUrl);
    }
}