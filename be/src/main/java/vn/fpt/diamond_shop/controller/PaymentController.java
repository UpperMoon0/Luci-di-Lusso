package vn.fpt.diamond_shop.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.service.IDeliveryService;
import vn.fpt.diamond_shop.service.IOrderService;
import vn.fpt.diamond_shop.service.IPaymentService;
import vn.fpt.diamond_shop.service.IUserService;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final IPaymentService paymentService;
    private final IOrderService orderService;
    private final IDeliveryService deliveryService;
    private final IUserService userService;

    @Autowired
    public PaymentController(IPaymentService paymentService,
                             IOrderService orderService,
                             IDeliveryService deliveryService,
                             IUserService userService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.deliveryService = deliveryService;
        this.userService = userService;
    }

    @PostMapping("/create-charge")
    public ResponseEntity<CommonResponse> createCharge(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestBody Map<String, String> body) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            String stripeToken = body.get("stripeToken");
            double totalPrice = orderService.createOrder(jwtToken);
            paymentService.createCharge(stripeToken, (int) (totalPrice * 100));

            //Create Delivery after finishing payment


            CommonResponse cr = new CommonResponse();
            cr.setMessage("Payment successful");
            return ResponseEntity.ok(cr);
        } catch (StripeException e) {
            CommonResponse cr = new CommonResponse();
            cr.setMessage("Payment failed");
            return ResponseEntity.badRequest().body(cr);
        } catch (InvalidJwtTokenException e) {
            CommonResponse cr = new CommonResponse();
            cr.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(cr);
        }
    }
}
