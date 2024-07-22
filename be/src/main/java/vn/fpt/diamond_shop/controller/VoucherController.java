package vn.fpt.diamond_shop.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.UpdateVoucherRequest;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.model.entity.Customer;
import vn.fpt.diamond_shop.model.entity.Voucher;
import vn.fpt.diamond_shop.service.IAccountService;
import vn.fpt.diamond_shop.service.IVoucherService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/voucher")
public class VoucherController {
    private final IVoucherService voucherService;
    private final IAccountService userService;

    @GetMapping("/get-all-vouchers")
    public ResponseEntity<List<Voucher>> getAllVouchers() {
        return ResponseEntity.ok(voucherService.getAllVouchers());
    }

    @GetMapping("/get-usable-vouchers")
    public ResponseEntity<List<Voucher>> getUsableVouchers() {
        List<Voucher> vouchers = voucherService.getAvailableVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @PostMapping("/create-voucher")
    public ResponseEntity<Void> createVoucher() {
        try {
            voucherService.createVoucher();
            return ResponseEntity.ok().build();
        } catch (SQLServerException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete-voucher")
    public ResponseEntity<Void> deleteVoucher(@RequestParam Long id) {
        try {
            voucherService.deleteVoucher(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-voucher")
    public ResponseEntity<Void> updateVoucher(@Valid @RequestBody UpdateVoucherRequest request) {
        try {
            voucherService.updateVoucher(request);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/redeem-voucher")
    public ResponseEntity<CommonResponse> addVoucherToCustomer(@RequestHeader("Authorization") String authorizationHeader,
                                                               @RequestBody Long voucherId) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            Account user = userService.findAccountByToken(jwtToken).orElse(null);

            if (user == null) {
                CommonResponse response = new CommonResponse();
                response.setMessage("User not found");
                return ResponseEntity.badRequest().body(response);
            }

            Customer customer = user.getCustomer();
            voucherService.redeemVoucher(voucherId, customer.getId());
            CommonResponse response = new CommonResponse();
            response.setMessage("Redeem voucher success!");

            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-my-vouchers")
    public ResponseEntity<List<Voucher>> getAllCustomerVouchers(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            Account user = userService.findAccountByToken(jwtToken).orElse(null);

            if (user == null) {
                return ResponseEntity.badRequest().body(null);
            }

            Customer customer = user.getCustomer();
            List<Voucher> vouchers = voucherService.getVouchersByCustomer(customer.getId());

            return ResponseEntity.ok(vouchers);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
