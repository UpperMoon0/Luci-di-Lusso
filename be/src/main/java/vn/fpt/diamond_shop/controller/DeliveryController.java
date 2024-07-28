package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.UnassignedDeliveriesResponse;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.AssignDelivererRequest;
import vn.fpt.diamond_shop.model.dto.MyDeliveriesResponse;
import vn.fpt.diamond_shop.model.entity.Delivery;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.service.IDeliveryService;
import vn.fpt.diamond_shop.service.IAccountService;
import vn.fpt.diamond_shop.service.IOrderItemService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final IAccountService userService;
    private final IDeliveryService deliveryService;
    private final IOrderItemService orderItemService;

    @GetMapping("/get-my-deliveries")
    public ResponseEntity<MyDeliveriesResponse> getMyDeliveries(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        Account user = userService.findAccountByToken(jwt).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Delivery> deliveries = deliveryService.getDeliveriesByAccount(user.getId());
        deliveries.removeIf(temp -> temp.getStatus().equals("COMPLETED"));

        MyDeliveriesResponse res = new MyDeliveriesResponse(deliveries, orderItemService);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/complete-delivery")
    public ResponseEntity<CommonResponse> completeDelivery(@RequestParam Long id) {
        deliveryService.completeDelivery(id);
        CommonResponse res = new CommonResponse();
        res.setMessage("Delivery completed successfully");
        return ResponseEntity.ok(res);
    }

    @PostMapping("assign-deliverer")
    public ResponseEntity<CommonResponse> assignDelivery(@RequestBody AssignDelivererRequest request) {
        System.out.println(request.getDeliveryId());
        System.out.println(request.getDelivererId());
        try {
            deliveryService.assignDeliverer(request.getDeliveryId(), request.getDelivererId());
            CommonResponse res = new CommonResponse();
            res.setMessage("Successfully assign deliverer!");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            CommonResponse res = new CommonResponse();
            res.setMessage("Something wrong while adding new delivery");
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/get-unassigned-deliveries")
    public ResponseEntity<UnassignedDeliveriesResponse> getUnassignedDeliveries() {
        List<Delivery> deliveries = deliveryService.getUnassignedDeliveries();
        UnassignedDeliveriesResponse res = new UnassignedDeliveriesResponse(deliveries, orderItemService);
        res.setMessage("Unassigned deliveries retrieved successfully");
        return ResponseEntity.ok(res);
    }
}
