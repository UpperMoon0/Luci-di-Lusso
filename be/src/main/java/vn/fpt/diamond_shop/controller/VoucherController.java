package vn.fpt.diamond_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fpt.diamond_shop.exception.InvalidJwtTokenException;
import vn.fpt.diamond_shop.model.dto.AddVoucherToAccountRequest;
import vn.fpt.diamond_shop.model.dto.CommonResponse;
import vn.fpt.diamond_shop.model.dto.GetAllCustomerVouchersResponse;
import vn.fpt.diamond_shop.model.dto.GetAllVouchersResponse;
import vn.fpt.diamond_shop.model.entity.Account;
import vn.fpt.diamond_shop.model.entity.Customer;
import vn.fpt.diamond_shop.model.entity.Voucher;
import vn.fpt.diamond_shop.service.IAccountService;
import vn.fpt.diamond_shop.service.IVoucherService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/voucher")
@RestController
public class VoucherController {

    private final IVoucherService voucherService;
    private final IAccountService userService;

    @Autowired
    public VoucherController(IVoucherService voucherService, IAccountService userService) {
        this.voucherService = voucherService;
        this.userService = userService;
    }

    @PostMapping("/add-voucher-to-account")
    public ResponseEntity<CommonResponse> addVoucherToAccount(@RequestHeader("Authorization") String authorizationHeader,
                                                              @Valid @RequestBody AddVoucherToAccountRequest body) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            Account user = userService.findAccountByToken(jwtToken).orElse(null);

            if (user == null) {
                CommonResponse response = new CommonResponse();
                response.setMessage("User not found");
                return ResponseEntity.badRequest().body(response);
            }

            Customer customer = user.getCustomer();
            voucherService.addVoucherToCustomer(body.getCode(), customer.getId());
            CommonResponse response = new CommonResponse();
            response.setMessage("Add voucher success!");

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

    @GetMapping("/get-all-customer-vouchers")
    public ResponseEntity<GetAllCustomerVouchersResponse> getAllCustomerVouchers(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String jwtToken = authorizationHeader.substring(7);
            Account user = userService.findAccountByToken(jwtToken).orElse(null);

            if (user == null) {
                GetAllCustomerVouchersResponse response = new GetAllCustomerVouchersResponse();
                response.setMessage("User not found");
                return ResponseEntity.badRequest().body(response);
            }

            Customer customer = user.getCustomer();
            List<Voucher> vouchers = voucherService.getAllCustomerVouchers(customer.getId());
            GetAllCustomerVouchersResponse response = new GetAllCustomerVouchersResponse();
            response.setMessage("Get vouchers!");
            response.setVouchers(vouchers);

            return ResponseEntity.ok(response);
        } catch (InvalidJwtTokenException e) {
            GetAllCustomerVouchersResponse response = new GetAllCustomerVouchersResponse();
            response.setMessage("Invalid JWT token");
            return ResponseEntity.badRequest().body(response);
        } catch (RuntimeException e) {
            GetAllCustomerVouchersResponse response = new GetAllCustomerVouchersResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-all-vouchers")
    public ResponseEntity<GetAllVouchersResponse> getAllVouchers() {
        GetAllVouchersResponse response = new  GetAllVouchersResponse();
        response.setVouchers(voucherService.getAllVouchers());
        return ResponseEntity.ok(response);
    }


}
