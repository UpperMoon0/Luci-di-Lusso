package vn.fpt.diamond_shop.controller;

import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.CreateChargeRequest;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.service.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final IPaymentService paymentService;
    private final IOrderService orderService;
    private final CartService cartService;
    private final IAccountService accountService;
    private final IVoucherService voucherService;
    private final IAccountService userService;
    private final OrderItemService orderItemService;
    private final IJewelryService jewelryService;

    @PostMapping("/create-charge")
    public ResponseEntity<CommonResponse> createCharge(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestBody CreateChargeRequest request){
        try {
            String jwtToken = authorizationHeader.substring(7);
            Account account = userService.findAccountByToken(jwtToken).orElse(null);

            if (account == null) {
                CommonResponse cr = new CommonResponse();
                cr.setMessage("User not found");
                return ResponseEntity.badRequest().body(cr);
            }

            Customer customer = account.getCustomer();

            String stripeToken = request.getStripeToken();
            long voucherId = request.getVoucherId();
            int discount = 0;
            if(voucherId != 0) {
                Voucher voucher = voucherService.useVoucher(voucherId, customer.getId());
                discount = voucher.getDiscount();
            }

            Order order = orderService.createOrderFromJwtToken(jwtToken, discount);
            List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);

            // Calculate total price
            int totalPrice = 0;
            for (OrderItem orderItem : orderItems) {
                totalPrice += jewelryService.calculateJewelryPriceWithSize(orderItem.getJewelry(), orderItem.getJewelrySize(), discount) * orderItem.getQuantity();
            }

            // Add point
            accountService.addPoint(customer, totalPrice);

            paymentService.createCharge(stripeToken, totalPrice * 100, order);
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
