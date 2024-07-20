package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.OrderDetailsResponse;
import vn.fpt.diamond_shop.model.dto.PurchaseHistoryResponse;
import vn.fpt.diamond_shop.model.entity.Customer;
import vn.fpt.diamond_shop.model.entity.Order;
import vn.fpt.diamond_shop.model.entity.OrderItem;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.service.IOrderItemService;
import vn.fpt.diamond_shop.service.IOrderService;
import vn.fpt.diamond_shop.service.IAccountService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final IOrderService orderService;
    private final IAccountService userService;
    private final IOrderItemService orderItemService;

    @Autowired
    public OrderController(IOrderService orderService,
                           IAccountService userService,
                           IOrderItemService orderItemService) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/get-purchase-history")
    public ResponseEntity<PurchaseHistoryResponse> getOrders(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        Account account = userService.findAccountByToken(jwt).orElse(null);

        if (account == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Customer customer = account.getCustomer();

        List<Order> orders = orderService.getOrdersByCustomer(customer.getId());

        PurchaseHistoryResponse res = new PurchaseHistoryResponse(orders, orderItemService);
        res.setMessage("Purchase history retrieved successfully");

        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-order-details")
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(@RequestParam Long orderId) {
        Order order = orderService.getOrderById(orderId);
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);

        OrderDetailsResponse res = new OrderDetailsResponse(order, orderItems);
        res.setMessage("Order details retrieved successfully");

        return ResponseEntity.ok(res);
    }
}
