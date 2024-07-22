package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.UpdateVoucherRequest;
import vn.fpt.diamond_shop.model.entity.Voucher;
import vn.fpt.diamond_shop.repository.IVoucherRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VoucherService implements IVoucherService {
    private final IVoucherRepository voucherRepository;

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public void updateVoucher(UpdateVoucherRequest request) {
        long id = request.getId();
        Voucher voucher = voucherRepository.findById(id).orElse(null);

        if (voucher == null) {
            throw new RuntimeException("Voucher not found");
        }

        voucher.setCode(request.getCode());
        voucher.setDiscount(request.getDiscount());
        voucher.setExpireAt(request.getExpireAt());
        voucher.setPrice(request.getPrice());
        voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucher(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public void createVoucher() {
        String uniqueCode = "VOUCHER_" + java.util.UUID.randomUUID();
        Voucher newVoucher = Voucher.builder()
            .code(uniqueCode)
            .discount(10)
            .createAt(LocalDateTime.now())
            .expireAt(LocalDateTime.now().plusDays(30).withNano(0))
            .price(1000)
            .build();

        voucherRepository.save(newVoucher);
    }
}
