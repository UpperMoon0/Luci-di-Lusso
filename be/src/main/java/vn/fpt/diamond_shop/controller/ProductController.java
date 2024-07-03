package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductController implements IProductController {
    private final IOrderRepository receiptRepository;
    private final IJewelryRepository jewelryRepository;
    private final IJewelryTypeRepository jewelryTagRepository;
    private final IJewelrySizeRepository jewelrySizeRepository;

    @Autowired
    public ProductController(IOrderRepository receiptRepository,
                             IJewelryRepository jewelryRepository,
                             IJewelryTypeRepository jewelryTagRepository,
                             IJewelrySizeRepository jewelrySizeRepository) {
        this.receiptRepository = receiptRepository;
        this.jewelryRepository = jewelryRepository;
        this.jewelryTagRepository = jewelryTagRepository;
        this.jewelrySizeRepository = jewelrySizeRepository;
    }

    @Override
    @PostMapping("/add-receipt")
    public ResponseEntity<CommonResponse> addReceipt(@RequestBody @Valid OrderRequest receiptRequest) {
        // Save receipt
        List<Long> jewelryIdList = receiptRequest.getJewelryIdList();
        Order order = new Order();
        order.setUserId(receiptRequest.getUserId());
        order.setTotalPrice(jewelryRepository.getTotalPriceByIdList(jewelryIdList));
        order.setCreateAt(LocalDateTime.now());
        order.setConfirmed(false);
        for (Long jewelryId : jewelryIdList) {
            jewelryRepository.findById(jewelryId).ifPresent(jewelry -> order.getJewelries().add(jewelry));
        }
        receiptRepository.save(order);

        // Return response
        CommonResponse response = new CommonResponse();
        response.setMessage("Receipt added successfully");
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/set-jewelry-size")
    public ResponseEntity<CommonResponse> setJewelrySize(@RequestBody @Valid SetJewelrySizeRequest req) {
        CommonResponse response = new CommonResponse();
        Jewelry jewelry = jewelryRepository.findById(req.getJewelryId()).orElse(null);
        if (jewelry != null) {
            JewelrySize jewelrySize = jewelrySizeRepository.findById(req.getSizeId());
            if (jewelrySize != null) {
                jewelry.setJewelrySize(jewelrySize);
                jewelryRepository.save(jewelry);
                response.setMessage("Set jewelry size successfully");
            } else {
                response.setMessage("Size not found");
            }
        } else {
            response.setMessage("Jewelry not found");
        }
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/confirm-order")
    public ResponseEntity<CommonResponse> confirmOrder(@RequestBody @Valid ConfirmOrder req) {
        CommonResponse response = new CommonResponse();
        Order receipt = receiptRepository.findById(req.getOrderId()).orElse(null);
        if (receipt != null) {
            receipt.setConfirmed(req.isConfirm());
            receiptRepository.save(receipt);
            response.setMessage("Confirm order successfully");
        } else {
            response.setMessage("Receipt not found");
        }
        return ResponseEntity.ok(response);
    }

    @Override
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
