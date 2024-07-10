package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.DeliveriesResponse;
import vn.fpt.diamond_shop.model.entity.Delivery;
import vn.fpt.diamond_shop.model.entity.User;
import vn.fpt.diamond_shop.service.IDeliveryService;
import vn.fpt.diamond_shop.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final IUserService userService;
    private final IDeliveryService deliveryService;

    @Autowired
    public DeliveryController(IUserService userService, IDeliveryService deliveryService) {
        this.userService = userService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/get-deliveries")
    public ResponseEntity<DeliveriesResponse> getDeliveries(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        User user = userService.getUserByToken(jwt);

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

}
