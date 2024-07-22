package vn.fpt.diamond_shop.service;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import vn.fpt.diamond_shop.model.dto.UpdateVoucherRequest;
import vn.fpt.diamond_shop.model.entity.Voucher;

import java.util.List;

public interface IVoucherService {
    List<Voucher> getAllVouchers();
    void updateVoucher(UpdateVoucherRequest request);
    void deleteVoucher(Long id);
    void createVoucher() throws SQLServerException;
    void redeemVoucher(Long voucherId, Long customerId);
    List<Voucher> getVouchersByCustomer(Long userId);
    Voucher useVoucher(Long voucherId, Long customerId);
    Voucher getVoucherById(Long id);
    List<Voucher> getAvailableVouchers();
}
