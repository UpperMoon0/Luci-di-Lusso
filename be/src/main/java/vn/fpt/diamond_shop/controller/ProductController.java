package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.constant.EJewelryTag;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.JewelriesRequest;
import vn.fpt.diamond_shop.model.dto.JewelriesResponse;
import vn.fpt.diamond_shop.model.dto.ReceiptRequest;
import vn.fpt.diamond_shop.model.entity.*;
import vn.fpt.diamond_shop.repository.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController implements IProductController {
    private final IReceiptRepository receiptRepository;
    private final IReceiptJewelryRepository receiptJewelryRepository;
    private final IJewelryRepository jewelryRepository;
    private final IJewelryTagRepository jewelryTagRepository;
    private final IJewelryJewelryTagRepository jewelryJewelryTagRepository;

    @Autowired
    public ProductController(IReceiptRepository receiptRepository,
                             IReceiptJewelryRepository receiptJewelryRepository,
                             IJewelryRepository jewelryRepository,
                             IJewelryTagRepository jewelryTagRepository,
                             IJewelryJewelryTagRepository jewelryJewelryTagRepository) {
        this.receiptRepository = receiptRepository;
        this.receiptJewelryRepository = receiptJewelryRepository;
        this.jewelryRepository = jewelryRepository;
        this.jewelryTagRepository = jewelryTagRepository;
        this.jewelryJewelryTagRepository = jewelryJewelryTagRepository;
    }

    @Override
    @PostMapping("/add-receipt")
    public ResponseEntity<CommonResponse> addReceipt(@Valid @RequestBody ReceiptRequest receiptRequest) {
        // Save receipt
        List<Long> jewelryIdList = receiptRequest.getJewelryIdList();
        Receipt receipt = new Receipt();
        receipt.setUserId(receiptRequest.getUserId());
        receipt.setTotalPrice(jewelryRepository.getTotalPriceByIdList(jewelryIdList));
        receipt.setCreateAt(LocalDateTime.now());
        receiptRepository.save(receipt);

        // Save receipt-jewelry
        jewelryIdList.forEach((jewelryId) -> {
            ReceiptJewelry receiptJewelry = new ReceiptJewelry();
            receiptJewelry.setReceiptId(receipt.getId());
            receiptJewelry.setJewelryId(jewelryId);
            receiptJewelry.setCreateAt(LocalDateTime.now());
            receiptJewelryRepository.save(receiptJewelry);
        });

        // Return response
        CommonResponse response = new CommonResponse();
        response.setMessage("Receipt added successfully");
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
                List<JewelryJewelryTag> jewelryJewelryTags = jewelryJewelryTagRepository.findByJewelryTagId(jewelryTag.getId());
                for (JewelryJewelryTag jjt : jewelryJewelryTags) {
                    Jewelry jewelry = jewelryRepository.findById(jjt.getJewelryId()).orElse(null);
                    if (jewelry != null && !jewelryList.contains(jewelry)) {
                        jewelryList.add(jewelry);
                    }
                }
            }
        }

        response.setJewelries(jewelryList);
        response.setMessage("Get jewelries successfully");

        return ResponseEntity.ok(response);
    }
}
