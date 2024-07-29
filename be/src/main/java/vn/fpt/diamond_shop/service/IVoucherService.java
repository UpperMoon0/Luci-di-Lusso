package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.dto.SaveVoucherRequest;
import vn.fpt.diamond_shop.model.entity.Voucher;

import java.util.List;

public interface IVoucherService {
    List<Voucher> getAllVouchers();
    void save(SaveVoucherRequest request);
    void toggleStatus(Long id);
    void redeemVoucher(Long voucherId, Long customerId);
    List<Voucher> getVouchersByCustomer(Long userId);
    Voucher useVoucher(Long voucherId, Long customerId);
    List<Voucher> getAvailableVouchers();
}
