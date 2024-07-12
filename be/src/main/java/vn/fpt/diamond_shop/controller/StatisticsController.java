package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fpt.diamond_shop.model.dto.StatisticsResponse;
import vn.fpt.diamond_shop.service.IOrderItemService;

import java.util.List;

@RequestMapping("/order")
@RestController
public class StatisticsController {
    private final IOrderItemService orderItemService;

    @Autowired
    public StatisticsController(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/get-sale-statistics")
    public ResponseEntity<StatisticsResponse> getSaleStatistics() {
        List<Integer> saleStatistics = orderItemService.getSaleStatistics();

        StatisticsResponse response = new StatisticsResponse();
        response.setStatistics(saleStatistics);
        response.setMessage("Get sale statistics successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-jewelries-sale-statistics")
    public ResponseEntity<StatisticsResponse> getJewelriesSaleStatistics() {
        List<Integer> saleStatistics = orderItemService.getJewelriesSaleStatistics();

        StatisticsResponse response = new StatisticsResponse();
        response.setStatistics(saleStatistics);
        response.setMessage("Get jewelries sale statistics successfully");
        return ResponseEntity.ok(response);
    }
}
