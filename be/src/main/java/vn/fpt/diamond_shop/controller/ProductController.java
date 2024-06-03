package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.ReceiptRequest;
import vn.fpt.diamond_shop.model.entity.Receipt;
import vn.fpt.diamond_shop.model.entity.ReceiptJewelry;
import vn.fpt.diamond_shop.repository.IJewelryRepository;
import vn.fpt.diamond_shop.repository.IReceiptJewelryRepository;
import vn.fpt.diamond_shop.repository.IReceiptRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController implements IProductController {
    private final IReceiptRepository receiptRepository;
    private final IReceiptJewelryRepository receiptJewelryRepository;
    private final IJewelryRepository jewelryRepository;

    @Autowired
    public ProductController(IReceiptRepository receiptRepository,
                             IReceiptJewelryRepository receiptJewelryRepository,
                             IJewelryRepository jewelryRepository) {
        this.receiptRepository = receiptRepository;
        this.receiptJewelryRepository = receiptJewelryRepository;
        this.jewelryRepository = jewelryRepository;
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
}
