package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.constant.EJewelryType;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/product")
@RestController
public class ProductController {
    private final IOrderRepository orderRepository;
    private final IJewelryRepository jewelryRepository;
    private final IJewelryTypeRepository jewelryTagRepository;
    private final IJewelrySizeRepository jewelrySizeRepository;
    private final IOrderItemRepository jewelryOrderRepository;

    @Autowired
    public ProductController(IOrderRepository receiptRepository,
                             IJewelryRepository jewelryRepository,
                             IJewelryTypeRepository jewelryTagRepository,
                             IJewelrySizeRepository jewelrySizeRepository,
                             IOrderItemRepository jewelryOrderRepository) {
        this.orderRepository = receiptRepository;
        this.jewelryRepository = jewelryRepository;
        this.jewelryTagRepository = jewelryTagRepository;
        this.jewelrySizeRepository = jewelrySizeRepository;
        this.jewelryOrderRepository = jewelryOrderRepository;
    }

    @GetMapping("/get-jewelry")
    public ResponseEntity<JewelryResponse> getJewelry(@RequestParam Long id) {
        Jewelry jewelry = jewelryRepository.findById(id).orElse(null);
        if (jewelry != null) {
            JewelryResponse response = new JewelryResponse();
            response.setId(jewelry.getId());
            response.setName(jewelry.getName());
            response.setDescription(jewelry.getDescription());
            response.setType(jewelry.getJewelryType().getType().getValue());
            response.setImageUrl(jewelry.getImageUrl());
            response.setPrice(jewelry.getPrice());
            response.setDiamondCut(jewelry.getDiamond().getCut().getCut().getValue());
            response.setDiamondClarity(jewelry.getDiamond().getClarity().getClarity().name());
            response.setDiamondColor(jewelry.getDiamond().getColor().getColor().name());
            response.setDiamondShape(jewelry.getDiamond().getShape().getShape().getValue());
            response.setMessage("Jewelry retrieved successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/get-all-jewelries")
    public ResponseEntity<JewelriesResponse> getAllJewelries() {
        JewelriesResponse response = new JewelriesResponse();
        List<Jewelry> jewelries = jewelryRepository.findAll();
        for (Jewelry jewelry : jewelries) {
            response.addJewelry(jewelry);
        }
        response.setMessage("Get all jewelries successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-jewelries")
    public ResponseEntity<JewelriesResponse> getJewelries(@RequestBody @Valid JewelriesRequest jr) {
        JewelriesResponse response = new JewelriesResponse();

        Stream<Jewelry> jewelryStream;
        if (jr.getTags().isEmpty()) {
            // If tag list is empty, return all jewelries
            jewelryStream = jewelryRepository.findAll().stream();
        } else {
            // If tag list is not empty, return jewelries that match the tags
            jewelryStream = jr.getTags().stream()
                .map(jewelryTagRepository::findByType)
                .flatMap(jewelryTag -> jewelryRepository.findAllByJewelryType(jewelryTag).stream())
                .distinct(); // Remove duplicates based on Jewelry's equals() and hashCode() methods
        }

        // Apply price filter if necessary
        if (!(jr.getMinPrice() == 0 && jr.getMaxPrice() == 0)) {
            jewelryStream = jewelryStream.filter(jewelry -> {
                double price = jewelry.getPrice();
                if (jr.getMaxPrice() == 0) {
                    return price >= jr.getMinPrice();
                } else {
                    return price >= jr.getMinPrice() && price <= jr.getMaxPrice();
                }
            });
        }

        List<Jewelry> filteredJewelries = jewelryStream.collect(Collectors.toList());
        for (Jewelry jewelry : filteredJewelries) {
            response.addJewelry(jewelry);
        }
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-order")
    public ResponseEntity<CommonResponse> addOrder(@RequestBody @Valid OrderRequest receiptRequest) {
        // Save receipt
        List<Long> jewelryIdList = receiptRequest.getJewelryIdList();
        Order order = new Order();
        order.setUserId(receiptRequest.getUserId());
        order.setTotalPrice(jewelryRepository.getTotalPriceByIdList(jewelryIdList));
        order.setCreateAt(LocalDateTime.now());
        order.setConfirmed(false);
        orderRepository.save(order);

        // Save jewelry order
        for (Long jewelryId : jewelryIdList) {
            OrderItem jewelryOrder = new OrderItem();
            jewelryOrder.setJewelry(jewelryRepository.findById(jewelryId).orElse(null));
            jewelryOrder.setOrder(order);
            jewelryOrder.setSize(null);
            jewelryOrderRepository.save(jewelryOrder);
        }

        // Return response
        CommonResponse response = new CommonResponse();
        response.setMessage("Receipt added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/set-jewelry-size")
    public ResponseEntity<CommonResponse> setJewelrySize(@RequestBody @Valid SetJewelrySizeRequest req) {
        CommonResponse response = new CommonResponse();
        Jewelry jewelry = jewelryRepository.findById(req.getJewelryId()).orElse(null);
        OrderItem jewelryOrder = jewelryOrderRepository.findByJewelry(jewelry).orElse(null);
        if (jewelryOrder != null) {
            JewelrySize size = jewelrySizeRepository.findById(req.getSizeId()).orElse(null);
            jewelryOrder.setSize(size);
            jewelryOrderRepository.save(jewelryOrder);
            response.setMessage("Set jewelry size successfully");
        } else {
            response.setMessage("Jewelry order not found");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirm-order")
    public ResponseEntity<CommonResponse> confirmOrder(@RequestBody @Valid ConfirmOrder req) {
        CommonResponse response = new CommonResponse();
        Order receipt = orderRepository.findById(req.getOrderId()).orElse(null);
        if (receipt != null) {
            receipt.setConfirmed(req.isConfirm());
            orderRepository.save(receipt);
            response.setMessage("Confirm order successfully");
        } else {
            response.setMessage("Receipt not found");
        }
        return ResponseEntity.ok(response);
    }

    //get tags to screen
    @GetMapping("/get-all-tags")
    public ResponseEntity<Object> getAllTags() {
        List<String> tags = new ArrayList<>();
        for (EJewelryType e : EJewelryType.values()) {
            tags.add(e.toString().substring(0,1).toUpperCase() + e.toString().substring(1).toLowerCase());
        }
        return ResponseEntity.ok(tags);
    }
}
