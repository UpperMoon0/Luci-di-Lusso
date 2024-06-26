package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import vn.fpt.diamond_shop.model.dto.*;

import javax.validation.Valid;

public interface IProductController {
    ResponseEntity<CommonResponse> addReceipt(@RequestBody @Valid ReceiptRequest receiptRequest);
    ResponseEntity<CommonResponse> setJewelrySize(@RequestBody @Valid SetJewelrySizeRequest req);
    ResponseEntity<JewelriesResponse> getAllJewelries(@RequestBody @Valid JewelriesRequest jr);
}
