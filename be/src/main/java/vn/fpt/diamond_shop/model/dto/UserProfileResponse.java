package vn.fpt.diamond_shop.model.dto;

import lombok.Getter;
import vn.fpt.diamond_shop.model.entity.Account;

import java.time.LocalDate;

@Getter
public class UserProfileResponse extends CommonResponse {
    private final String fullName;
    private final String address;
    private final String phone;
    private final String imageUrl;
    private final LocalDate dob;

    public UserProfileResponse(Account user) {
        this.fullName = user.getCustomer().getFullName();
        this.address = user.getCustomer().getAddress();
        this.phone = user.getCustomer().getPhone();
        this.imageUrl = user.getCustomer().getImageUrl();
        this.dob = user.getCustomer().getDob();
    }
}
