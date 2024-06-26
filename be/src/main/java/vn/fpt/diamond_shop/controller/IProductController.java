package vn.fpt.diamond_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import vn.fpt.diamond_shop.model.dto.*;

import javax.validation.Valid;

public interface IProductController {
    ResponseEntity<CommonResponse> addReceipt(@RequestBody @Valid OrderRequest receiptRequest);
    ResponseEntity<CommonResponse> setJewelrySize(@RequestBody @Valid SetJewelrySizeRequest req);
    ResponseEntity<CommonResponse> confirmOrder(@RequestBody @Valid ConfirmOrder req);
    ResponseEntity<JewelriesResponse> getAllJewelries(@RequestBody @Valid JewelriesRequest jr);
}
