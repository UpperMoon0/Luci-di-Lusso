package vn.fpt.diamond_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.dto.SaveVoucherRequest;
import vn.fpt.diamond_shop.model.entity.Customer;
import vn.fpt.diamond_shop.model.entity.Voucher;
import vn.fpt.diamond_shop.repository.ICustomerRepository;
import vn.fpt.diamond_shop.repository.IVoucherRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VoucherService implements IVoucherService {
    private final IVoucherRepository voucherRepository;
    private final ICustomerRepository customerRepository;

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public void save(SaveVoucherRequest request) {
        Voucher voucher;
        if (request.getId() == 0) {
            voucher = new Voucher();
        } else {
            voucher = voucherRepository.findById(request.getId()).orElse(null);
            if (voucher == null) {
                throw new RuntimeException("Voucher not found");
            }
        }

        // Voucher expiration date must be after today
        if (request.getExpireAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Voucher expiration date must be after today");
        }

        voucher.setCode(request.getCode());
        voucher.setDiscount(request.getDiscount());
        voucher.setExpireAt(request.getExpireAt());
        voucher.setPrice(request.getPrice());
        try {
            voucherRepository.save(voucher);
        } catch (Exception e) {
            throw new RuntimeException("Voucher already exists");
        }
    }

    @Override
    public void toggleStatus(Long id) {
        Voucher voucher = voucherRepository.findById(id).orElse(null);

        if (voucher == null) {
            throw new RuntimeException("Voucher not found");
        }

        if (voucher.getStatus().equals("ACTIVE")) {
            voucher.setStatus("INACTIVE");
        } else {
            voucher.setStatus("ACTIVE");
        }

        voucherRepository.save(voucher);
    }

    @Override
    public void redeemVoucher(Long voucherId, Long customerId) {
        Voucher voucher = voucherRepository.findById(voucherId).orElse(null);

        if (voucher == null) {
            throw new RuntimeException("Voucher not found");
        }

        // Check if voucher is expired
        if (voucher.getExpireAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Voucher is expired");
        }

        Customer customer = customerRepository.getById(customerId);

        // Check if customer has enough points
        if (customer.getPoint() < voucher.getPrice()) {
            throw new RuntimeException("Not enough points");
        }

        customer.setPoint(customer.getPoint() - voucher.getPrice());

        customer.getVouchers().add(voucher);

        customerRepository.save(customer);
    }

    @Override
    public List<Voucher> getVouchersByCustomer(Long customerId) {
        Customer customer = customerRepository.getById(customerId);
        List<Voucher> vouchers = customer.getVouchers();
        // Filter vouchers that expired
        vouchers.removeIf(v -> v.getExpireAt().isBefore(LocalDateTime.now()));

        return vouchers;
    }

    @Override
    public Voucher useVoucher(Long voucherId, Long customerId) {
        Voucher voucher = voucherRepository.findById(voucherId).orElse(null);

        if (voucher == null) {
            throw new RuntimeException("Voucher not found");
        }

        Customer customer = customerRepository.getById(customerId);

        if (customer.getVouchers().stream().noneMatch(v -> v.getId().equals(voucherId))) {
            throw new RuntimeException("Voucher not found");
        }

        customer.getVouchers().remove(voucher);
        customerRepository.save(customer);

        return voucher;
    }

    @Override
    public List<Voucher> getAvailableVouchers() {
        return voucherRepository.findByExpireAtAfter(LocalDateTime.now());
    }
}
