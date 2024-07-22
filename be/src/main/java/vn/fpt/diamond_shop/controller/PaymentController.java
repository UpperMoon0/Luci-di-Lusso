package vn.fpt.diamond_shop.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.service.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final IPaymentService paymentService;
    private final IOrderService orderService;
    private final CartService cartService;
    private final IAccountService accountService;
    private final IVoucherService voucherService;
    private final IAccountService userService;

    @Autowired
    public PaymentController(IPaymentService paymentService,
                             IOrderService orderService, CartService cartService,
                             IAccountService accountService,
                             IVoucherService voucherService,
                             IAccountService userService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.cartService = cartService;
        this.accountService = accountService;
        this.voucherService = voucherService;
        this.userService = userService;
    }

    @PostMapping("/create-charge")
    public ResponseEntity<CommonResponse> createCharge(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestBody Map<String, String> body) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            Account user = userService.findAccountByToken(jwtToken).orElse(null);

            String stripeToken = body.get("stripeToken");
            String voucherCode = body.get("voucherCode");
            double totalPrice = orderService.createOrderFromJwtToken(jwtToken);
            //Add point
            accountService.addPoint(jwtToken, totalPrice);
            //Starting to charge (cost can be reduced by vouchers but will not affect the amount of points user gets)
            int discount = 0;
            if(!voucherCode.isEmpty() && !voucherCode.isBlank()) {
                discount = voucherService.useVoucher(voucherCode, user.getCustomer().getId()).getDiscount();
            }

            paymentService.createCharge(stripeToken, (int) (totalPrice * 100 * (1 - discount/100)));
            cartService.deleteAllCartItems(jwtToken);

            CommonResponse cr = new CommonResponse();
            cr.setMessage("Payment successful");
            return ResponseEntity.ok(cr);
        } catch (StripeException e) {
            CommonResponse cr = new CommonResponse();
            cr.setMessage("Payment failed.");
            return ResponseEntity.badRequest().body(cr);
        } catch (RuntimeException e) {
            CommonResponse cr = new CommonResponse();
            cr.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(cr);
        }
    }
}
