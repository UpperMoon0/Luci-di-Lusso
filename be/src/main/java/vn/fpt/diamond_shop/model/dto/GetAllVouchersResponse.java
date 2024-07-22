package vn.fpt.diamond_shop.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.fpt.diamond_shop.model.entity.Voucher;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetAllVouchersResponse extends CommonResponse {
    List<Voucher> vouchers;
}
