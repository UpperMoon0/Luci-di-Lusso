package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.constant.EJewelryTag;
import vn.fpt.diamond_shop.model.dto.*;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping("/product")
@RestController
public class ProductController implements IProductController {
    private final IOrderRepository receiptRepository;
    private final IJewelryRepository jewelryRepository;
    private final IJewelryTagRepository jewelryTagRepository;
    private final IJewelrySizeRepository jewelrySizeRepository;

    @Autowired
    public ProductController(IOrderRepository receiptRepository,
                             IJewelryRepository jewelryRepository,
                             IJewelryTagRepository jewelryTagRepository,
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
    @GetMapping("/get-all-jewelries")
    public ResponseEntity<JewelriesResponse> getAllJewelries(@RequestBody @Valid JewelriesRequest jr) {
        JewelriesResponse response = new JewelriesResponse();

        List<Jewelry> jewelryList;
        if (jr.getTags().isEmpty()) {
            // If tag list is empty, return all jewelries
            jewelryList = jewelryRepository.findAll();
        } else {
            // If tag list is not empty, return jewelries that match the tags
            jewelryList = new ArrayList<>();
            for (EJewelryTag tag : jr.getTags()) {
                JewelryTag jewelryTag = jewelryTagRepository.findByTag(tag);
                List<Jewelry> jewelries = jewelryRepository.findAllByJewelryTags(jewelryTag);
                jewelryList.addAll(jewelries);
                // Remove duplicate jewelries

            }
        }

        response.setJewelries(jewelryList);
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
    }
}
