package vn.fpt.diamond_shop.service;

import vn.fpt.diamond_shop.model.entity.Voucher;

import java.util.List;

public interface IVoucherService {
    void addVoucherToCustomer(String code, Long userId);
    List<Voucher> getAllCustomerVouchers(Long userId);
    Voucher useVoucher(String code, Long customerId);
    List<Voucher> getAllVouchers();
}
