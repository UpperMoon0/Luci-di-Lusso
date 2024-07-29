package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.DeliveriesResponse;
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

        List<Delivery> deliveries = deliveryService.findByAccount(user.getId());
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
    public ResponseEntity<CommonResponse> assignDeliverer(@RequestBody AssignDelivererRequest request) {
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

    @PutMapping("unassign-deliverer")
    public ResponseEntity<CommonResponse> unassignDeliverer(@RequestParam Long deliveryId) {
        try {
            deliveryService.unassignDeliverer(deliveryId);
            CommonResponse res = new CommonResponse();
            res.setMessage("Successfully unassign deliverer!");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            CommonResponse res = new CommonResponse();
            res.setMessage("Something wrong while unassigning deliverer");
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/get-all-deliveries")
    public ResponseEntity<DeliveriesResponse> getAllDeliveries() {
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        DeliveriesResponse res = new DeliveriesResponse(deliveries, orderItemService);
        res.setMessage("All deliveries retrieved successfully");
        return ResponseEntity.ok(res);
    }
}
