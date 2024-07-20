package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.CreateDeliveryRequest;
import vn.fpt.diamond_shop.model.dto.DeliveriesResponse;
import vn.fpt.diamond_shop.model.dto.UnassignedDeliveriesResponse;
import vn.fpt.diamond_shop.model.entity.Delivery;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.service.IDeliveryService;
import vn.fpt.diamond_shop.service.IAccountService;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final IAccountService userService;
    private final IDeliveryService deliveryService;

    @Autowired
    public DeliveryController(IAccountService userService, IDeliveryService deliveryService) {
        this.userService = userService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/get-deliveries")
    public ResponseEntity<DeliveriesResponse> getDeliveries(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        Account user = userService.findAccountByToken(jwt).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Delivery> deliveries = deliveryService.getDeliveriesByUser(user.getId());
        System.out.println("Number of deliveries: " + deliveries.size());
        deliveries.removeIf(temp -> temp.getStatus().name().equals("DONE"));
        System.out.println("Number of deliveries2 : " + deliveries.size());

        DeliveriesResponse res = new DeliveriesResponse(deliveries);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/complete-delivery")
    public ResponseEntity<CommonResponse> completeDelivery(@RequestParam Long id) {
        deliveryService.completeDelivery(id);
        CommonResponse res = new CommonResponse();
        res.setMessage("Delivery completed successfully");
        return ResponseEntity.ok(res);
    }

    @PostMapping("add-delivery")
    public ResponseEntity<CommonResponse> addDelivery(@RequestBody CreateDeliveryRequest request) {

        try {
            deliveryService.assignDeliverer(request.getDeliveryID(), request.getDelivererID());
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
        UnassignedDeliveriesResponse res = new UnassignedDeliveriesResponse(deliveries);
        res.setMessage("Unassigned deliveries retrieved successfully");
        return ResponseEntity.ok(res);
    }
}
