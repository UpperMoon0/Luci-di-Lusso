package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.JewelriesRequest;
import vn.fpt.diamond_shop.model.dto.JewelriesResponse;
import vn.fpt.diamond_shop.model.dto.ReceiptRequest;

import javax.validation.Valid;

public interface IProductController {
    ResponseEntity<CommonResponse> addReceipt(@RequestBody @Valid ReceiptRequest receiptRequest);
    ResponseEntity<JewelriesResponse> getAllJewelries(@RequestBody @Valid JewelriesRequest jr);
}
