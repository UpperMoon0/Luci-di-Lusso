package vn.fpt.diamond_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.SaveVoucherRequest;
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
        List<Voucher> vouchers = voucherService.getAllVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/get-usable-vouchers")
    public ResponseEntity<List<Voucher>> getUsableVouchers() {
        List<Voucher> vouchers = voucherService.getAvailableVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @DeleteMapping("/toggle-voucher-status")
    public ResponseEntity<Void> toggleVoucherStatus(@RequestParam Long id) {
        try {
            voucherService.toggleStatus(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/save-voucher")
    public ResponseEntity<CommonResponse> saveVoucher(@Valid @RequestBody SaveVoucherRequest request) {
        try {
            voucherService.save(request);
            CommonResponse response = new CommonResponse();
            response.setMessage("Voucher saved successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            CommonResponse response = new CommonResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/redeem-voucher")
    public ResponseEntity<CommonResponse> redeemVoucher(@RequestHeader("Authorization") String authorizationHeader,
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
            response.setMessage("Redeem voucher success");

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
            List<Voucher> activeVouchers = vouchers.stream()
                    .filter(voucher -> "ACTIVE".equals(voucher.getStatus()))
                    .toList();

            return ResponseEntity.ok(activeVouchers);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
