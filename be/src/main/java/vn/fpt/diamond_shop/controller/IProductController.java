package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.JewelriesResponse;
import vn.fpt.diamond_shop.model.dto.ReceiptRequest;

public interface IProductController {
    ResponseEntity<CommonResponse> addReceipt(ReceiptRequest receiptRequest);
    ResponseEntity<JewelriesResponse> getAllJewelries();
}
