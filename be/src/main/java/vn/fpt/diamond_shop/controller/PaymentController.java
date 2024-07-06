package vn.fpt.diamond_shop.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.CreateChargeRequest;
import vn.fpt.diamond_shop.service.IPaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final IPaymentService paymentService;

    @Autowired
    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-charge")
    public ResponseEntity<CommonResponse> createCharge(@RequestBody CreateChargeRequest request) {
        String customerName = request.getCustomerName();
        String token = request.getToken();
        Integer amount = request.getAmount();
        try {
            paymentService.createCharge(token, amount);
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
