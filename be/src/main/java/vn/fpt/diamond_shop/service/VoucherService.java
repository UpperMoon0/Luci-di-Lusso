package vn.fpt.diamond_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.diamond_shop.model.entity.Customer;
import vn.fpt.diamond_shop.model.entity.Voucher;
import vn.fpt.diamond_shop.repository.ICustomerRepository;
import vn.fpt.diamond_shop.repository.IVoucherRepository;

import java.util.List;

@Service
public class VoucherService implements IVoucherService {

    private final IVoucherRepository voucherRepository;
    private final ICustomerRepository customerRepository;

    @Autowired
    public VoucherService(IVoucherRepository voucherRepository, ICustomerRepository customerRepository) {
        this.voucherRepository = voucherRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void addVoucherToCustomer(String code, Long customerId) {
        Voucher voucher = voucherRepository.findByCode(code).orElse(null);
        Customer customer = customerRepository.getById(customerId);

        customer.setPoint(customer.getPoint() - voucher.getPrice());

        customer.getVouchers().add(voucher);

        customerRepository.save(customer);
    }

    @Override
    public List<Voucher> getAllCustomerVouchers(Long customerId) {
        Customer customer = customerRepository.getById(customerId);

        return customer.getVouchers().stream().toList();
    }

    @Override
    public Voucher useVoucher(String code, Long customerId) {
        Voucher voucher = voucherRepository.findByCode(code).orElse(null);
        Customer customer = customerRepository.getById(customerId);

        customer.getVouchers().remove(voucher);
        customerRepository.save(customer);

        return voucher;
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

}
