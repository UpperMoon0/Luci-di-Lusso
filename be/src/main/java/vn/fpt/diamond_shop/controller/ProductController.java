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
import java.util.Objects;

import static org.springframework.http.ResponseEntity.ok;

@RequestMapping("/product")
@RestController
public class ProductController {
    private final IOrderRepository orderRepository;
    private final IJewelryRepository jewelryRepository;
    private final IJewelryTypeRepository jewelryTagRepository;
    private final IJewelrySizeRepository jewelrySizeRepository;
    private final IJewelryOrderRepository jewelryOrderRepository;

    @Autowired
    public ProductController(IOrderRepository receiptRepository,
                             IJewelryRepository jewelryRepository,
                             IJewelryTypeRepository jewelryTagRepository,
                             IJewelrySizeRepository jewelrySizeRepository,
                             IJewelryOrderRepository jewelryOrderRepository) {
        this.orderRepository = receiptRepository;
        this.jewelryRepository = jewelryRepository;
        this.jewelryTagRepository = jewelryTagRepository;
        this.jewelrySizeRepository = jewelrySizeRepository;
        this.jewelryOrderRepository = jewelryOrderRepository;
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
            JewelryOrder jewelryOrder = new JewelryOrder();
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
        JewelryOrder jewelryOrder = jewelryOrderRepository.findByJewelry(jewelry).orElse(null);
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

    @PostMapping("/get-all-jewelries")
    public ResponseEntity<JewelriesResponse> getAllJewelries(@RequestBody @Valid JewelriesRequest jr) {
        JewelriesResponse response = new JewelriesResponse();

        List<Jewelry> jewelryList;
        if (jr.getTags().isEmpty()) {
            // If tag list is empty, return all jewelries
            jewelryList = jewelryRepository.findAll();
        } else {
            // If tag list is not empty, return jewelries that match the tags
            jewelryList = new ArrayList<>();
            for (EJewelryType tag : jr.getTags()) {
                JewelryType jewelryTag = jewelryTagRepository.findByType(tag);
                List<Jewelry> jewelries = jewelryRepository.findAllByJewelryType(jewelryTag);
                //remove duplicates
                for (Jewelry temp1 : jewelries) {
                    for (Jewelry temp2 : jewelries.subList(jewelries.indexOf(temp1)+1, jewelries.toArray().length-1)) {
                        if (Objects.equals(temp1.getId(), temp2.getId())) {
                            jewelries.remove(temp2);
                        }
                    }
                }
                for (Jewelry jewelry : jewelries) {
                    if (!(jr.getMinPrice() == 0 && jr.getMaxPrice() == 0)) {
                        if (jr.getMaxPrice() == 0) {
                            if (jewelry.getPrice() < jr.getMinPrice()) {
                                jewelries.remove(jewelry);
                            }
                        } else {
                            if (jewelry.getPrice() < jr.getMinPrice() || jewelry.getPrice() > jr.getMaxPrice()) {
                                jewelries.remove(jewelry);
                            }
                        }
                    }
                }
            }
        }

        response.setJewelries(jewelryList);
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-jewelry")
    public ResponseEntity<JewelryResponse> getJewelryById(@RequestParam Long id) {
        Jewelry jewelry = jewelryRepository.findById(id).orElse(null);
        if (jewelry != null) {
            JewelryResponse response = new JewelryResponse();
            response.setName(jewelry.getName());
            response.setDescription(jewelry.getDescription());
            response.setType(jewelry.getJewelryType().getType().getValue());
            response.setImageUrl(jewelry.getImageUrl());
            response.setPrice(jewelry.getPrice());
            response.setDiamondCut(jewelry.getDiamond().getCut().getCut().getValue());
            response.setDiamondClarity(jewelry.getDiamond().getClarity().getClarity().name());
            response.setDiamondPolish(jewelry.getDiamond().getPolish().getPolish().name());
            response.setDiamondShape(jewelry.getDiamond().getShape().getShape().getValue());
            response.setMessage("Jewelry retrieved successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
