package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.DeliveryResponse;
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
    public ResponseEntity<DeliveryResponse> getDeliveries(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        User user = userService.getUserByToken(jwt);
        DeliveryResponse res = new DeliveryResponse();

        List<Delivery> deliveries = deliveryService.getDeliveries(user.getId());
        for (Delivery temp : deliveries) {
            if (temp.getStatus().getValue().equalsIgnoreCase("Done")) {
                deliveries.remove(temp);
            }
        }

        res.setDeliveries(deliveries);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/check-status")
    public ResponseEntity<CommonResponse> checkDeliveryStatus(@RequestParam Long id) {
        String msg = deliveryService.checkDeliveryStatus(id);
        CommonResponse res = new CommonResponse();
        res.setMessage(msg);
        return ResponseEntity.ok(res);
    }

}
