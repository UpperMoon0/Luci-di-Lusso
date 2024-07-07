package vn.fpt.diamond_shop.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.service.IOrderService;
import vn.fpt.diamond_shop.service.IPaymentService;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final IPaymentService paymentService;
    private final IOrderService orderService;

    @Autowired
    public PaymentController(IPaymentService paymentService,
                             IOrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @PostMapping("/create-charge")
    public ResponseEntity<CommonResponse> createCharge(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestBody Map<String, String> body) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            String stripeToken = body.get("stripeToken");
            double totalPrice = orderService.createOrder(jwtToken);
            paymentService.createCharge(stripeToken, (int) (totalPrice * 100));
            CommonResponse cr = new CommonResponse();
            cr.setMessage("Payment successful");
            return ResponseEntity.ok(cr);
        } catch (StripeException e) {
            CommonResponse cr = new CommonResponse();
            cr.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(cr);
        }
    }
}
