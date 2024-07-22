package vn.fpt.diamond_shop.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.model.dto.UpdateVoucherRequest;
import vn.fpt.diamond_shop.model.entity.Voucher;
import vn.fpt.diamond_shop.service.IVoucherService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/voucher")
public class VoucherController {
    private final IVoucherService voucherService;

    @GetMapping("/get-all-vouchers")
    public ResponseEntity<List<Voucher>> getAllVouchers() {
        return ResponseEntity.ok(voucherService.getAllVouchers());
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
        voucherService.deleteVoucher(id);
        return ResponseEntity.ok().build();
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
}
